package com.url_shortener.utils;

import com.url_shortener.exception.ConverterException;

public class IdUrlConverter {
    public static char[] getShortUrl(Long id) throws ConverterException {
        char[] result = new char[ConstantsClass.ID_SIZE];
        for (int i = 0; i < ConstantsClass.ID_SIZE; ++i) {
            result[i] = Base62Converter.get((int)(id % 62));
            id /= 62;
        }
        return result;
    }

    public static Long getId(char[] shortUrl) throws ConverterException {
        long id = 0L;
        for (int i = ConstantsClass.ID_SIZE - 1; i >= 0; --i) {
            id = id * 62 + Base62Converter.get(shortUrl[i]);
        }
        return id;
    }
}
