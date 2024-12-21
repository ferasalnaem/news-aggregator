package com.news.newsaggregator.repository;

import com.news.newsaggregator.model.UncategorizedArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UncategorizedArticleRepository extends MongoRepository<UncategorizedArticle, String> {
}
