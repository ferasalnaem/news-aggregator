package com.news.newsaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableScheduling
@EnableMongoRepositories
public class NewsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsApiApplication.class, args);
	}

}
