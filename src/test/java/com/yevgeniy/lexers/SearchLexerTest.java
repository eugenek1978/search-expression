package com.yevgeniy.lexers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


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
    void tokenize_simple_token(ArgumentsAccessor argumentsAccessor) throws Exception {
        String searchString = removeQuotes(argumentsAccessor.getString(0));

        Lexer searchLexer = new SearchLexer();
        Token actualToken = searchLexer.tokenize(searchString).get(0);
        Token expectedToken = new Token(Type.valueOf(argumentsAccessor.getString(1)), argumentsAccessor.getString(2));

        assertEquals(expectedToken, actualToken);
    }


    @ParameterizedTest
    @CsvSource({"\"'Yevgeniy' AND 'Yoav'\" ,PHRASE AND PHRASE",
            "\"'Yevgeniy' 'Yoav'\" ,PHRASE PHRASE",
            "\"'Yevgeniy' OR NOT 'Yoav'\" ,PHRASE OR NOT PHRASE",
            "\"NOT ('Yevgeniy' OR NOT 'Yoav') \" ,NOT LPAREN PHRASE OR NOT PHRASE RPAREN"})
    void tokenize_several_tokens(String searchRequest, String expectedTokens) throws Exception {
        String searchString = removeQuotes(searchRequest);

        Lexer searchLexer = new SearchLexer();
        List<Token> actualTokens = searchLexer.tokenize(searchString);


        assertEquals(Arrays.stream(expectedTokens.split(" ")).collect(Collectors.toList()), actualTokens.stream().map(token -> token.getType().toString()).collect(Collectors.toList()));
    }
    @ParameterizedTest
    @CsvSource({"\"'Yevgeniy\"",
            "\"Yevgeniy'\" ",
            " OT "})
    void tokenize_several_failure(String searchRequest) throws Exception {
        String searchString = removeQuotes(searchRequest);

        Lexer searchLexer = new SearchLexer();


        assertThrows(Exception.class ,() -> searchLexer.tokenize(searchString));
    }

    private String removeQuotes(String searchString) {
        return searchString.substring(1, searchString.length() - 1);
    }
}