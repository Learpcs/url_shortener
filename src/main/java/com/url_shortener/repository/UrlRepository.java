package com.url_shortener.repository;

import com.url_shortener.entity.UrlDao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UrlRepository extends JpaRepository<UrlDao, Long> {
    List<UrlDao> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UrlDao u WHERE TIMESTAMPDIFF(MINUTE, u.lastAccessDate, CURRENT_TIMESTAMP) > :X")
    void deleteUrlsOlderThanXMinutes(@Param("X") Long X);

    @Modifying
    @Transactional
    @Query("UPDATE UrlDao u SET u.redirectCount = u.redirectCount + 1, u.lastAccessDate = CURRENT_TIMESTAMP WHERE u.urlId = :urlId")
    void updateUrlAccess(@Param("urlId") Long urlId);
}
