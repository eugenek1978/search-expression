package com.yevgeniy.controllers;

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

    @PostMapping("api/tokenize")
    public List<Token> tokenize (@RequestBody String searchString) throws Exception {
        return lexerService.tokenize(searchString);
    }

}
