package com.url_shortener.repository;

import com.url_shortener.repository.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {

    @Query("from UserDao where login=?1 and password=?2")
    Optional<UserDao> Authentificate(String username, String password);
}
