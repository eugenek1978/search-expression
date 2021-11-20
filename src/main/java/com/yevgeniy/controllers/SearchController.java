package com.yevgeniy.controllers;

import com.yevgeniy.lexers.Token;
import com.yevgeniy.lexers.Type;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @PostMapping(path = "api/tokenize")
    public List<Token> tokenize (@RequestBody String searchString){
        return List.of(Token.builder().type(Type.PHRASE).value("Hello").build());
    }

}
