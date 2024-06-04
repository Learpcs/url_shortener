package com.url_shortener.controller;

import com.url_shortener.controller.request.UrlDto;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.service.UrlService;
import com.url_shortener.service.UserService;
import com.url_shortener.utils.Mappers.DaoMapper;
import com.url_shortener.utils.ShortUrlRandomizer;
import com.url_shortener.utils.Validity.UrlFixer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//TODO Service, URLVALIDATION, TESTS

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final UserService userService;
    private final DaoMapper daoMapper;
    private final ShortUrlRandomizer shortUrlRandomizer;
    private final UrlFixer urlFixer;

    @PostMapping("/create")
    public HttpStatus create(@RequestBody UrlDto urlDto) throws DatabaseException, UrlValidationException, ConverterException {
        // Этот код должен быть внутри UrlService.create() в мапперах не хорошо вызывать внешние сервисы
        // Такой коммент в целом касается всех контроллеров, они не должны хранить сервисную логику
        urlDto = urlFixer.fix(urlDto);
        // Особенно здесь, мы сразу в контроллере создаем сущность, которая потом передается в контроллер и он просто кладет ее в базу
        // Создавать сущность должен сам сервис по своим правилам, чтобы потом эту логику можно было допустим вызвать в другом контроллере или сервисе
        if (!urlService.create(daoMapper.map(urlDto, userService))) {
            throw new DatabaseException("ID already exists (probably)");
        }
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/get")
    public HttpStatus get(@RequestBody UrlDto urlDto) throws DatabaseException, UrlValidationException, ConverterException {
        // 1. Код ниже должен быть также в сервисе
        // 2. Метод get должен только проверять наличие URL, а не лениво его создавать, так как это нарушает принципы REST сервисов,
        //    что метод GET не изменяет состояние запрашиваемого объекта.
        //    Пользователь обычно не ожидает, что вызывая метод GET он что-то создаст
        urlDto = urlFixer.fix(urlDto);

        for (int tt = 0; tt < 100; ++tt) {
            urlDto = new UrlDto(urlDto.url(), shortUrlRandomizer.randomize(), urlDto.creatorToken());
            if (urlService.create(daoMapper.map(urlDto, userService))) {
                return HttpStatus.ACCEPTED;
            }
        }
        throw new DatabaseException("We ran out of space, wtf");
    }
}

