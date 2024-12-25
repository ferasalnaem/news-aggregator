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

@Component
public class NewsApiAdapterImpl implements NewsApiAdapter {

    @Value("${newsapi.url}")
    private final String apiUrl= null;

    @Value("${newsapi.key}")
    private final String apiKey= null; // Example API key

    @Override
    public List<UncategorizedArticle> fetchArticles(String country) {
        List<UncategorizedArticle> articles = new ArrayList<>();

        // Fetch the response from the NewsAPI (This is just a simplified example)
        try {
            // Create the request URL with query parameters.
            // Add "pageSize=20" to limit the number of articles retrieved.

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

                    // Map JSON fields to the UncategorisedArticle object.
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

    // You can implement other methods as needed, such as fetching by category
 /*   @Override
    public List<UncategorizedArticle> fetchArticlesByCategory(String category) {
        // similar logic, but including category in the API URL
        return fetchArticles();
    }*/

    private String makeApiRequest(String url) {
        // Simple method for making an HTTP request (this would be expanded in a real implementation)
        // It makes a GET request and returns the response as a String.
        return ""; // Placeholder
    }

    private Date convertStringToDate(String publishedAtString) {

        Date publishedAtDate;
        // Convert publishedAt from String to Date
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            publishedAtDate = isoFormat.parse(publishedAtString);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format in API response: " + publishedAtString, e);
        }
        return publishedAtDate;
    }
}

