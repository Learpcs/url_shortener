package com.url_shortener.repository;

import com.url_shortener.controller.dto.UrlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<UrlDto, Long> {

}