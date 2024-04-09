package com.url_shortener.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileHelper {
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    public FileHelper(String fileName) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(fileName));
    }

    public String nextToken() throws IOException {
        while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        }
        return stringTokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public Long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }
}
