package com.url_shortener.service.impl;

import com.url_shortener.repository.UrlRepository;
import com.url_shortener.repository.entity.UrlDao;
import com.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    // Инжектить лучше через конструтор, чтобы класс был тестируемый без лишней рефлексии,
    // так же его можно будет сделать иммутабельным с помощью final полей,
    // что безопасней для многопоточности
    @Autowired
    private UrlRepository urlRepository;


    // В случае с create лучше или возвращать созданную сущность или ничего (void),
    // то что по какой-то причине объект не был создан должен говорить эксепшен,
    // а не возвращаемый boolean, на который пользователи метода могут и забить
    @Override
    public boolean create(UrlDao urlDao) {
        if (findById(urlDao.getId()).orElse(null) != null) {
            return false;
        }
        urlRepository.save(urlDao);
        return true;
    }

    @Override
    public Optional<UrlDao> findById(Long id) {
        return urlRepository.findById(id);
    }

    // То же что с create() методом, только здесь лучше просто void возвращать
    @Override
    public boolean deleteById(Long id) {
        if (findById(id).orElse(null) == null) {
            return false;
        }
        urlRepository.deleteById(id);
        return true;
    }
}
