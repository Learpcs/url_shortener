package com.url_shortener.utils.Mappers;

import com.url_shortener.exception.ConverterException;

public enum Base62Mapper {
    ;

    public static char get(final int i) throws ConverterException{
        if (26 > i)
            return (char)(i + 'a');
        else if (52 > i)
            return (char)(i - 26 + 'A');
        else if (62 > i)
            return (char)(i - 52 + '0');
        else
            throw new ConverterException("Invalid index of alphabet: " + i);
    }
    public static int get(final char ch) throws ConverterException{
        if ('a' <= ch && 'z' >= ch)
            return ch - 'a';
        else if ('A' <= ch && 'Z' >= ch)
            return ch - 'A' + 26;
        else if ('0' <= ch && '9' >= ch)
            return ch - '0' + 52;
        else
            throw new ConverterException("Character is not base62: " + ch);

    }
}
