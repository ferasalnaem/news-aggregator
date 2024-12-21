package com.news.newsaggregator.controller;

import com.news.newsaggregator.model.CategorizedArticle;
import com.news.newsaggregator.model.UncategorizedArticle;
import com.news.newsaggregator.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<List<CategorizedArticle>> getNews(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String fromDate,
            @RequestParam(defaultValue = "publishedAt") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        List<CategorizedArticle> categorizedNews = newsService.getNews(category, title, author, fromDate, sortBy, order);
        return ResponseEntity.ok(categorizedNews);
    }
}

