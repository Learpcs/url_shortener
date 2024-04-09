package com.url_shortener.database;

import com.url_shortener.exception.DatabaseException;
import com.url_shortener.utils.FileHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

public class DatabaseImpl implements Database, AutoCloseable {
    Long[] ids;
    String[] shorts, longs;
    String fileName;

    DatabaseImpl(String filename) throws DatabaseException {
        fileName = filename;
        FileHelper  reader;
        try {
            reader = new FileHelper(fileName);
        } catch(Exception e) {
            throw new DatabaseException("Database not found: " + e.getMessage());
        }

        int n;
        try {
            n = reader.nextInt();
        } catch(Exception e) {
            throw new DatabaseException("Size number is broken: " + e.getMessage());
        }
        ids = new Long[n];
        shorts = new String[n];
        longs = new String[n];

        for (int i = 0; i < n; ++i) {
            Long id; String longURL, shortURL;
            try {
                id = reader.nextLong();
            }
            catch (Exception e) {
                throw new DatabaseException("id on line " + (i + 1) + " is broken: " + e.getMessage());
            }

            try {
                longURL = reader.nextToken();
            }
            catch (Exception e) {
                throw new DatabaseException("longURL on line " + (i + 1) + " is broken: " + e.getMessage());
            }

            try {
                shortURL = reader.nextToken();
            }
            catch (Exception e) {
                throw new DatabaseException("shortURL on line " + (i + 1) + " is broken: " + e.getMessage());
            }

            for (int j = 0; j < i; ++j) {
                if (ids[j].equals(id)) {
                    throw new DatabaseException("id column contains duplicates, line " + (i + 1));
                }
                if (longs[j].equals(longURL)) {
                    throw new DatabaseException("short column contains duplicates, line " + (i + 1));
                }
                if (shorts[j].equals(shortURL)) {
                    throw new DatabaseException("short column contains duplicates, line " + (i + 1));
                }
            }


            ids[i] = id;
            longs[i] = longURL;
            shorts[i] = shortURL;
        }
    }

    @Override
    public void createShortURL(Long id, String longURL, String shortURL) throws DatabaseException {
        if (!ids.add(id)) {
            throw new DatabaseException("id column contains duplicates, createShortUrl()");
        }
        if (!longs.add(longURL)) {
            throw new DatabaseException("short column contains duplicates, createShortUrl()");
        }
        if(!shorts.add(shortURL)) {
            throw new DatabaseException("short column contains duplicates, createShortUrl()");
        }
    }

    @Override
    public String getShortURLById(Long id) throws DatabaseException {
        if (!ids.contains(id)) {
            throw new DatabaseException("id doesn't exist, getShortURLById()");
        }
        return shortByID.get(id);
    }

    @Override
    public String getLongURLById(Long id) throws DatabaseException {
        if (!ids.contains(id)) {
            throw new DatabaseException("id doesn't exist, getShortURLById()");
        }
        return longByID.get(id);
    }

    @Override
    public String getShortURL(String longURL) throws DatabaseException {
        if (!ids.contains(id)) {
            throw new DatabaseException("id doesn't exist, getShortURLById()");
        }
        return longByID.get(id);
    }

    @Override
    public String getLongURL(String shortURL) throws DatabaseException {
        return "";
    }

    @Override
    public Long[] getAllIDs() throws DatabaseException {
        return new Long[0];
    }

    @Override
    public String[] getAllLongUrls() throws DatabaseException {
        return new String[0];
    }

    @Override
    public String[] getAllShortUrls() throws DatabaseException {
        return new String[0];
    }

    @Override
    public String deleteById(Long id) throws DatabaseException {
        return "";
    }

    @Override
    public String[] getAllUrl() throws DatabaseException {
        return new String[0];
    }

    @Override
    public String deleteById(long id) throws DatabaseException {
        return "";
    }

    @Override
    public String deleteShortURL(String longURL) throws DatabaseException {
        return "";
    }

    @Override
    public String deleteLongURL(String shortURL) throws DatabaseException {
        return "";
    }

    @Override
    public void close() throws Exception {
        PrintWriter printWriter = new PrintWriter("")
    }
}
