package com.news.newsaggregator.service;

import com.news.newsaggregator.model.CategorizedArticle;
import com.news.newsaggregator.model.UncategorizedArticle;
import com.news.newsaggregator.repository.CategorizedArticleRepository;
import com.news.newsaggregator.repository.UncategorizedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CategorizedArticleRepository categorizedArticleRepository;



    public List<CategorizedArticle> getNews(String category, String title, String author, String fromDate, String sortBy, String order) {


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
            query.addCriteria(Criteria.where("publishedAt").gte(fromDate));
        }

        // Add sorting
        if (sortBy != null && order != null) {
            query.with(Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
        }

        // Execute the query
        return mongoTemplate.find(query, CategorizedArticle.class);
    }
}
