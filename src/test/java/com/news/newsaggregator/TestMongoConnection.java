package com.news.newsaggregator;


import com.news.newsaggregator.model.UncategorizedArticle;
import com.news.newsaggregator.repository.UncategorizedArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestMongoConnection {

    @Autowired
    private UncategorizedArticleRepository repository;

    @Test
    void testSaveAndFetchArticle() throws ParseException {
        // Save a test article
        UncategorizedArticle article = new UncategorizedArticle();
        article.setTitle("Test Article");
        article.setDescription("This is a test article");
        article.setContent("Test content");
        article.setAuthor("John Doe");
        article.setUrl("http://example.com");
        article.setSource("Example Source");
        // Set the publishedAt date
        String dateString = "2024-11-11";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date publishedDate = dateFormat.parse(dateString); // Parse string to Date
        article.setPublishedAt(publishedDate); // Set the Date object

        repository.save(article);

        // Fetch all articles and assert
        Iterable<UncategorizedArticle> articles = repository.findAll();
        assertThat(articles).isNotEmpty();
    }
}


