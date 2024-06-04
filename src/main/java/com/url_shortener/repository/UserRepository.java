package com.url_shortener.repository;

import com.url_shortener.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    @Query("from UserDao u where u.username = :username and u.password = :password")
    Optional<UserDao> auth(@Param("username") String username, @Param("password") String password);

    @Query("select case when count(u) > 0 then true else false end from UserDao u where u.username = :username")
    Boolean existByUsername(@Param("username") String username);
}
