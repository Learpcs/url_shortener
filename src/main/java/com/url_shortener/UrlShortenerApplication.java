package com.url_shortener;

import com.url_shortener.exception.DatabaseException;
import com.url_shortener.utils.IdUrlConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) throws DatabaseException {
		try {
			System.out.println(IdUrlConverter.getShortUrl(128L));
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
