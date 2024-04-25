package com.url_shortener;

import com.url_shortener.exception.DatabaseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) throws DatabaseException {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
