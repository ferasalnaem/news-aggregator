package com.news.newsaggregator.adapter;

import com.news.newsaggregator.model.UncategorizedArticle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class NewsApiAdapterImpl implements NewsApiAdapter {

    @Value("${newsapi.url}")
    private final String apiUrl= null;

    @Value("${newsapi.key}")
    private final String apiKey= null;

    @Override
    public List<UncategorizedArticle> fetchArticles(String country) {
        List<UncategorizedArticle> articles = new ArrayList<>();

        try {
            // Create the request URL with query parameters.
            // Add "pageSize=50" to limit the number of articles retrieved.

            String url = String.format("%s?country=%s&pageSize=50&apiKey=%s", apiUrl, country, apiKey);

            // Use RestTemplate to send the HTTP GET request.
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(url, String.class);

            // Parse the JSON response.
            JSONObject response = new JSONObject(jsonResponse);

            // Check if the status is "ok" and parse articles.
            if ("ok".equalsIgnoreCase(response.getString("status"))) {
                JSONArray articlesArray = response.getJSONArray("articles");

                for (int i = 0; i < articlesArray.length(); i++) {
                    JSONObject articleJson = articlesArray.getJSONObject(i);

                    // Map JSON fields to the UncategorizedArticle object.
                    UncategorizedArticle article = new UncategorizedArticle();
                    article.setId(articleJson.optString("url"));
                    article.setTitle(articleJson.optString("title"));
                    article.setDescription(articleJson.optString("description"));
                    article.setContent(articleJson.optString("content"));
                    article.setAuthor(articleJson.optString("author"));
                    article.setPublishedAt(convertStringToDate(articleJson.optString("publishedAt")));
                    article.setUrl(articleJson.optString("url"));
                    article.setUrlToImage(articleJson.optString("urlToImage"));
                    article.setSource(articleJson.getJSONObject("source").optString("name"));

                    // Add to the list of articles.
                    articles.add(article);
                }
            }
        } catch (Exception e) {
            // Handle exceptions like network errors or JSON parsing issues.
            System.err.println("Error fetching articles from NewsAPI: " + e.getMessage());
        }
        return articles;
    }

    private Date convertStringToDate(String publishedAtString) {

        Date publishedAtDate;
        // Convert publishedAt from String to Date
        //TODO: Make sure to set the date in UTC
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            publishedAtDate = isoFormat.parse(publishedAtString);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format in API response: " + publishedAtString, e);
        }
        return publishedAtDate;
    }
}

