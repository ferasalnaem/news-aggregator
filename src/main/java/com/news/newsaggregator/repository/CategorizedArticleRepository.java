package com.news.newsaggregator.repository;

import com.news.newsaggregator.model.CategorizedArticle;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CategorizedArticleRepository extends MongoRepository<CategorizedArticle, String> {
}
