package com.url_shortener.repository;

import com.url_shortener.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {

    // Лучше использвать именованные параметры в запросах, чтобы случайно не перепутать их порядок
    @Query("from UserDao where login=?1 and password=?2")
    Optional<UserDao> Authentificate(String username, String password);
}
