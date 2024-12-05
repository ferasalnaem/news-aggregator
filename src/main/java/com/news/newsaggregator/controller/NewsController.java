package com.news.newsaggregator.controller;

import com.news.newsaggregator.model.CategorizedArticle;
import com.news.newsaggregator.repository.CategorizedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class NewsController {

    private final CategorizedArticleRepository categorizedArticleRepository;

    @Autowired
    public NewsController(CategorizedArticleRepository categorizedArticleRepository) {
        this.categorizedArticleRepository = categorizedArticleRepository;
    }

    @GetMapping("/api/news")
    public List<CategorizedArticle> getLatestNews() {
        return categorizedArticleRepository.findAll();  // Get all news from the DB
    }
}

