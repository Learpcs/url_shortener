package com.url_shortener.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/premium_user").setViewName("hello");
        registry.addViewController("/admintest").setViewName("hello");
        registry.addViewController("/home").setViewName("hello");
        registry.addViewController("/private").setViewName("hello");
    }
}