package com.url_shortener.repository;

import com.url_shortener.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    @Query("from UserDao u where u.username = :username")
    Optional<UserDao> findByUsername(@Param("username") String username);
}
