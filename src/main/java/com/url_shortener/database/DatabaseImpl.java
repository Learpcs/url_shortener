package com.url_shortener.database;

import com.sun.source.tree.Tree;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.utils.FileHelper;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Service
@Getter
public class DatabaseImpl implements AutoCloseable {
    private HashMap<Long, String> IDToURL;
    private HashMap<String, Long> URLToID;
    static private final String fileName = "database.txt";

    public DatabaseImpl() throws DatabaseException {
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

        IDToURL = new HashMap<>();
        URLToID = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            Long id; String nowURL;
            try {
                id = reader.nextLong();
            }
            catch (Exception e) {
                throw new DatabaseException("id on line " + (i + 1) + " is broken: " + e.getMessage());
            }

            try {
                nowURL = reader.nextToken();
            }
            catch (Exception e) {
                throw new DatabaseException("nowURL on line " + (i + 1) + " is broken: " + e.getMessage());
            }


            addToDatabase(id, nowURL);
        }
    }

    public void addToDatabase(Long ID, String URL) throws DatabaseException {
        if (IDToURL.put(ID, URL) != null)
            throw new DatabaseException("ID already exists: " + ID + " " + URL);
        if (URLToID.put(URL, ID) != null)
            throw new DatabaseException("URL already exists: " + ID + " " + URL);
    }

    public void removeFromDatabase(Long ID, String URL) throws DatabaseException {
        if (!IDToURL.remove(ID, URL))
            throw new DatabaseException("ID does not exists: " + ID + " " + URL);
        if (!URLToID.remove(URL, ID))
            throw new DatabaseException("URL does not exists: " + ID + " " + URL);
    }

    @Override
    public void close() throws Exception {
        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.println(IDToURL.size());

        TreeMap<Long, String> sortedMap = new TreeMap<>(IDToURL);
        for (Map.Entry<Long, String> x : sortedMap.entrySet()) {
            printWriter.println(x.getKey() + " "  + x.getValue());
        }
    }
}
