package com.news.newsaggregator.controller;

import com.news.newsaggregator.service.NewsAggregatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aggregator")
public class AggregatorController {

    private final NewsAggregatorService newsAggregatorService;

    public AggregatorController(NewsAggregatorService newsAggregatorService) {
        this.newsAggregatorService = newsAggregatorService;
    }

    @PostMapping("/fetch")
    public ResponseEntity <String> fetchAndSaveNews() {
        System.out.println("Triggering NewsAggregatorService...");
        newsAggregatorService.aggregateAndStoreArticles("us");
        return ResponseEntity.ok("Fetched and saved uncategorized news");
    }
}

