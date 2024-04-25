package com.url_shortener.utils.Mappers;

import com.url_shortener.exception.ConverterException;

//TODO jwt token mapping
public class TokenMapper {
    public static String getToken(Long id) throws ConverterException {
        return String.valueOf(id);
    }

    public static Long getUserId(String creatorToken) throws ConverterException {
        return Long.parseLong(creatorToken);
    }
}
