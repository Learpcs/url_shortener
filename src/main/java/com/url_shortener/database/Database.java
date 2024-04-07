package com.url_shortener.database;

import com.url_shortener.exception.DatabaseException;

public interface Database {
    String getShortenedURL(String URL) throws DatabaseException;
    String[] getAllUrl() throws DatabaseException;
    void createShortenedURL(String URL) throws DatabaseException;



}
