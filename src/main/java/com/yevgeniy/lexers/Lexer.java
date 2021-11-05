package com.yevgeniy.lexers;

public interface Lexer {

    public Token get_next_token() throws Exception;

}
