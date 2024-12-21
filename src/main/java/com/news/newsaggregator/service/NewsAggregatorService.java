package com.news.newsaggregator.service;

import com.news.newsaggregator.adapter.NewsApiAdapter;
import com.news.newsaggregator.model.UncategorizedArticle;
import com.news.newsaggregator.repository.UncategorizedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*
This service fetches news from external APIs
 */
@Service
public class NewsAggregatorService {

    private final NewsApiAdapter newsApiAdapter;
    private final UncategorizedArticleRepository uncategorizedArticleRepository;

    // Inject the adapter and the repository
    @Autowired
    public NewsAggregatorService(NewsApiAdapter newsApiAdapter, UncategorizedArticleRepository uncategorizedArticleRepository) {
        this.newsApiAdapter = newsApiAdapter;
        this.uncategorizedArticleRepository = uncategorizedArticleRepository;
    }

    // This method aggregates articles from one or more sources (APIs)
    public void aggregateAndStoreArticles(String country) {
        List<UncategorizedArticle> articles = newsApiAdapter.fetchArticles(country);

        // Save the uncategorized articles to the MongoDB database
        uncategorizedArticleRepository.saveAll(articles);
    }

    // This method can be used to aggregate and store articles from multiple sources
    public void aggregateArticlesFromMultipleSources(String country) {
        // Example: If you want to fetch articles from multiple APIs, you can call multiple adapters
        List<UncategorizedArticle> articlesFromApi1 = newsApiAdapter.fetchArticles(country);
       /* List<UncategorizedArticle> articlesFromApi2 = anotherNewsApiAdapter.fetchArticles();*/

        // Combine and save
        List<UncategorizedArticle> allArticles = new ArrayList<>();
        allArticles.addAll(articlesFromApi1);
    /*    allArticles.addAll(articlesFromApi2);*/

        uncategorizedArticleRepository.saveAll(allArticles);
    }
}

