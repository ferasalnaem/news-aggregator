package com.news.newsaggregator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.List;

@Document(collection = "uncategorized_articles")
public class UncategorizedArticle {
    @Id
    private String id; // MongoDB ObjectId
    private String title;
    private String description;
    private String content;
    private String author;
    private String source;
    private String url;
    private String urlToImage;
    @Field("publishedAt")
    private Date publishedAt;
    private List<String> tags;
    private String imageUrl;
    private final boolean isCategorized = false;


    public String getId() {
        return id;
    }

    public void setId(String url) {
        this.id = DigestUtils.sha256Hex(url);;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

