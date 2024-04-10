package com.url_shortener.utils;

import com.url_shortener.exception.ConverterException;

public class Base62Converter {
    public static char get(int i) throws ConverterException{
        if (i < 26)
            return (char)(i + 'a');
        else if (i < 52)
            return (char)(i - 26 + 'A');
        else if (i < 62)
            return (char)(i - 52 + '0');
        else
            throw new ConverterException("Invalid index of alphabet: " + i);
    }
    public static int get(char ch) throws ConverterException{
        if ('a' <= ch && ch <= 'z')
            return ch - 'a';
        else if ('A' <= ch && ch <= 'Z')
            return ch - 'A' + 26;
        else if ('0' <= ch && ch <= '9')
            return ch - '0' + 52;
        else
            throw new ConverterException("Character is not base62: " + ch);

    }
}
