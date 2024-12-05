package com.news.newsaggregator.repository;

import com.news.newsaggregator.model.CategorizedArticle;
import com.news.newsaggregator.model.UncategorizedArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategorizedArticleRepository extends MongoRepository<CategorizedArticle, String> {
    List<CategorizedArticle> findByCategory(String category);
    List<CategorizedArticle> findByTagsIn(List<String> tags);
    List<CategorizedArticle> findByCategoryAndTagsIn(String category, List<String> tags);
}
