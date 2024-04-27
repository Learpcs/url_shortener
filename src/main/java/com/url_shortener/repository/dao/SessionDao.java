package com.url_shortener.repository.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDao {
    @Id
    Long id;

    Date expirationDate;

    @ManyToOne(targetEntity = UserDao.class)
    UserDao ownerId;
}
