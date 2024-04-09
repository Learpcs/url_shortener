package com.url_shortener.database;

import com.url_shortener.exception.DatabaseException;

public interface Database {
    //CREATE
    void createShortURL(Long id, String longURL, String shortURL) throws DatabaseException;

    //READ
    String getShortURLById(Long id) throws DatabaseException;
    String getLongURLById(Long id) throws DatabaseException;
    String getShortURL(String longURL) throws DatabaseException;
    String getLongURL(String shortURL) throws DatabaseException;

    Long[] getAllIDs() throws DatabaseException;
    String[] getAllLongUrls() throws DatabaseException;
    String[] getAllShortUrls() throws DatabaseException;

    //DELETE
    String deleteById(Long id) throws DatabaseException;
    String deleteShortURL(String longURL) throws DatabaseException;
    String deleteLongURL(String shortURL) throws DatabaseException;
}
