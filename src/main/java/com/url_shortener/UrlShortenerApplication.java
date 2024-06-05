package com.url_shortener;

import com.url_shortener.exception.DatabaseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlShortenerApplication {

	public static void main(String[] args) throws DatabaseException {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
