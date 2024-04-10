package com.url_shortener.repository.dao;

import com.url_shortener.controller.dto.URLDTO;
import com.url_shortener.exception.DatabaseException;

public interface URLDAO {
    void create(URLDTO URL) throws DatabaseException;
    URLDTO findByID(Long ID) throws DatabaseException;
    URLDTO findByURL(String URL) throws DatabaseException;
    void delete(URLDTO URL) throws DatabaseException;
}
