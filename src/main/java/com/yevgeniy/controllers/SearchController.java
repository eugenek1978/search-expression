package com.yevgeniy.controllers;

import com.yevgeniy.controllers.dto.SearchRequestDto;
import com.yevgeniy.lexers.Token;
import com.yevgeniy.services.LexerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    LexerService lexerService;

    @PostMapping("/api/tokens")
    public List<Token> getTokens (@RequestBody SearchRequestDto searchRequest) throws Exception {
        return lexerService.tokenize(searchRequest.getSearchString());
    }

}
