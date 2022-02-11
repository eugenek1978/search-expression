package com.yevgeniy.controllers;

import com.yevgeniy.controllers.dto.SearchRequestDto;
import com.yevgeniy.lexers.Token;
import com.yevgeniy.services.LexerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SearchController {

    @Autowired
    LexerService lexerService;

    @PostMapping("/api/tokens")
    public CollectionModel<Token> getTokens (@RequestBody @Valid SearchRequestDto searchRequest) throws Exception {
        return CollectionModel.of(lexerService.tokenize(searchRequest.getSearchString()));
    }

}
