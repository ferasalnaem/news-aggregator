package com.news.newsaggregator.adapter;

import com.news.newsaggregator.model.UncategorizedArticle;
import org.springframework.stereotype.Component;

import java.util.List;

public interface NewsApiAdapter {
    // This method fetches articles from the news API
    List<UncategorizedArticle> fetchArticles(String country);

}

