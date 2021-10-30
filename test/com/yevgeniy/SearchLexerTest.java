package com.yevgeniy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SearchLexerTest {
    @ParameterizedTest
    @CsvSource({"\"'Yevgeniy'\" ,PHRASE, Yevgeniy",
            "\"'Yoav'\" ,PHRASE, Yoav ",
            "\"  'Yoav'  \" ,PHRASE, Yoav ",
            "\" NOT  \", NOT, NOT",
            "\" OR  \", OR, OR",
            "\" AND  \", AND, AND",
            "\" (  \", LPAREN, ( ",
            "\" ) \", RPAREN, ) "})
    void get_next_token_simple_token( ArgumentsAccessor argumentsAccessor) throws Exception {
        String searchString = removeQuotes(argumentsAccessor.getString(0));

        Lexer searchLexer = new SearchLexer(searchString);
        Token actualToken = searchLexer.get_next_token();
        Token expectedToken = new Token(Type.valueOf(argumentsAccessor.getString(1)),argumentsAccessor.getString(2));

        assertEquals(expectedToken,actualToken);
    }

    private String removeQuotes(String searchString) {
        return searchString.substring(1, searchString.length() - 1);
    }
}