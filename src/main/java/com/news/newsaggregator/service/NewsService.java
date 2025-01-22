package com.news.newsaggregator.service;

import com.news.newsaggregator.model.CategorizedArticle;
import com.news.newsaggregator.repository.CategorizedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CategorizedArticleRepository categorizedArticleRepository;



    public List<CategorizedArticle> getNews(String category, String title, String author, String fromDate, String sortBy, String order, int page, int limit) {


        Query query = new Query();

        // Add optional filters if parameters are not null
        if (category != null){
            query.addCriteria(Criteria.where("category").regex(category, "i"));
        }
        if (title != null) {
            query.addCriteria(Criteria.where("title").regex(title, "i")); // Case-insensitive regex
        }
        if (author != null) {
            query.addCriteria(Criteria.where("author").regex(author, "i"));
        }
        if (fromDate != null) {
            try {

                // Define the start and end of the day
                LocalDateTime startOfDay = LocalDate.parse(fromDate).atStartOfDay();
                LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
                // Add range criteria to query
                query.addCriteria(Criteria.where("publishedAt").gte(startOfDay).lte(endOfDay));

            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format for 'fromDate'. Expected format: yyyy-MM-dd", e);
            }
        }

        // Pagination logic (skip and limit)
        query.skip(page * limit);  // Skip articles for previous pages
        query.limit(limit);  // Limit the number of articles per page

        // Add sorting
        if (sortBy != null && order != null) {
            query.with(Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
        }

        // Execute the query
        return mongoTemplate.find(query, CategorizedArticle.class);
    }
}

