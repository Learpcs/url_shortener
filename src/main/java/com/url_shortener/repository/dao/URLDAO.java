package com.url_shortener.repository.dao;

import com.url_shortener.controller.dto.URLDTO;

public interface URLDAO {
    URLDTO findById(long id);
    void save(URLDTO url);
    //.....
}
