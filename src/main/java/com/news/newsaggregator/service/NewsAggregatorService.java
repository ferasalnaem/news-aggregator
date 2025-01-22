package com.news.newsaggregator.service;

import com.news.newsaggregator.adapter.NewsApiAdapter;
import com.news.newsaggregator.model.UncategorizedArticle;
import com.news.newsaggregator.repository.UncategorizedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
This service fetches news from external APIs
 */
@Service
public class NewsAggregatorService {

    private final NewsApiAdapter newsApiAdapter;
    private final UncategorizedArticleRepository uncategorizedArticleRepository;

    @Autowired
    public NewsAggregatorService(NewsApiAdapter newsApiAdapter, UncategorizedArticleRepository uncategorizedArticleRepository) {
        this.newsApiAdapter = newsApiAdapter;
        this.uncategorizedArticleRepository = uncategorizedArticleRepository;
    }

    public void aggregateAndStoreArticles(String country) {
        List<UncategorizedArticle> articles = newsApiAdapter.fetchArticles(country);

        // Filter out articles that already exist in the database and save only new articles
        List<UncategorizedArticle> newArticles = articles.stream()
                .filter(article -> !uncategorizedArticleRepository.existsById(article.getId()))
                .collect(Collectors.toList());
        if (!newArticles.isEmpty()) {
            uncategorizedArticleRepository.saveAll(newArticles);
        }
    }
}

