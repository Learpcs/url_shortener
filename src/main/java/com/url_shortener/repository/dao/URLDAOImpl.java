package com.url_shortener.repository.dao;

import com.url_shortener.controller.dto.URLDTO;
import com.url_shortener.database.DatabaseImpl;
import com.url_shortener.exception.DatabaseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class URLDAOImpl implements URLDAO {
    DatabaseImpl database;

    @Override
    public void create(URLDTO url) throws DatabaseException {
        database.addToDatabase(url.ID(), url.URL());
    }

    public URLDTO findByID(Long id) throws DatabaseException {
        return new URLDTO(id, Optional.ofNullable(database.getIDToURL().get(id)).orElseThrow(() -> new DatabaseException("non-existent id")));
    }

    @Override
    public URLDTO findByURL(String URL) throws DatabaseException {
        return new URLDTO(Optional.ofNullable(database.getURLToID().get(URL)).orElseThrow(() -> new DatabaseException("non-existent URL")), URL);

    }

    @Override
    public void delete(URLDTO url) throws DatabaseException {
        database.removeFromDatabase(url.ID(), url.URL());
    }
}
