package com.yevgeniy.services;

import com.yevgeniy.lexers.Lexer;
import com.yevgeniy.lexers.SearchLexer;
import com.yevgeniy.lexers.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LexerService {


    public List<Token> tokenize (String searchString) throws Exception {
        Lexer searchLexer = new SearchLexer();
        return searchLexer.tokenize(searchString);
    }
}
