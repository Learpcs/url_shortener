package com.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlShortenerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
