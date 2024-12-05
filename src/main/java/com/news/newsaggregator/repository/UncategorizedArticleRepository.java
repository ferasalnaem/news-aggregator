package com.news.newsaggregator.repository;

import com.news.newsaggregator.model.UncategorizedArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UncategorizedArticleRepository extends MongoRepository<UncategorizedArticle, String> {
    List<UncategorizedArticle> findByTitleContaining(String keyword);
    List<UncategorizedArticle> findByTagsIn(List<String> tags);
}
