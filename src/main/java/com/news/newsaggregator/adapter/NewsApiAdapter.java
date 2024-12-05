package com.news.newsaggregator.adapter;

import com.news.newsaggregator.model.UncategorizedArticle;
import org.springframework.stereotype.Component;

import java.util.List;

public interface NewsApiAdapter {
    // This method will fetch articles from the news API
    List<UncategorizedArticle> fetchArticles(String country);

    // This method can be used to fetch articles by specific parameters if needed
/*
    List<UncategorizedArticle> fetchArticlesByCategory(String category);
*/
}

