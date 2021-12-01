package com.yevgeniy.services;

import com.yevgeniy.lexers.Token;

import java.util.List;

public interface LexerService {
    List<Token> tokenize (String searchString) throws Exception;
}
