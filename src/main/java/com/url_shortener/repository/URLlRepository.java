package com.url_shortener.repository;

import org.springframework.da

import com.url_shortener.exception.DatabaseException;
import com.url_shortener.repository.dao.URLDAO;


public interface URLlRepository extends CrudRepository<URLDAO, Long> {
    //CREATE
    void save(URLDAO urldao) throws DatabaseException;

    //READ
    String getURLByID(Long ID) throws DatabaseException;
    String getIDByURL(String URL) throws DatabaseException;

    Long[] getAllIDs() throws DatabaseException;
    String[] getAllURLs() throws DatabaseException;

    //DELETE
    String delete(URLDAO urldao) throws DatabaseException;
}
