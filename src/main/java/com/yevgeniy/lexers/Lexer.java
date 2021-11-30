package com.yevgeniy.lexers;

import java.util.List;

public interface Lexer {

     Token getNextToken() throws Exception;

     List<Token>  tokenize (String text) throws Exception;

}
