package com.yevgeniy.services;

import com.yevgeniy.lexers.Lexer;
import com.yevgeniy.lexers.SearchLexer;
import com.yevgeniy.lexers.Token;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LexerService {

    Lexer searchLexer = new SearchLexer();

    public List<Token> tokenize (String searchString) throws Exception {
        return searchLexer.tokenize(searchString);
    }
}
