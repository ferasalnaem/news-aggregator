package com.news.newsaggregator.service;

import com.news.newsaggregator.aggregator.NewsAggregatorService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class NewsAggregatorRunner {

    private final NewsAggregatorService newsAggregatorService;

    public NewsAggregatorRunner(NewsAggregatorService newsAggregatorService) {
        this.newsAggregatorService = newsAggregatorService;
    }

    @PostConstruct
    public void triggerOnStartup() {
        System.out.println("Triggering NewsAggregatorService...");
        newsAggregatorService.aggregateAndStoreArticles("us");
    }
}

