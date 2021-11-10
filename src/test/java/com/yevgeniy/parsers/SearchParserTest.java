package com.yevgeniy.parsers;

import com.yevgeniy.lexers.Token;
import com.yevgeniy.lexers.Type;
import com.yevgeniy.parsers.ast.AST;
import com.yevgeniy.parsers.ast.BinOperator;
import com.yevgeniy.parsers.ast.Phrase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchParserTest {

    @Test
    void parse_phrase (){
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        AST expected = Phrase.builder().value(hello).build();
        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        AST actual = parser.parse(tokens);
        assertEquals(expected,actual);
    }

    @Test
    void parse_one_and (){
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        Token world = Token.builder().value("World").type(Type.PHRASE).build();
        Token and = Token.builder().value("AND").type(Type.AND).build();

        AST expected =
                BinOperator.builder()
                .leftChild(Phrase.builder().value(hello).build())
                .rightChild(Phrase.builder().value(world).build())
                .value(and)
                .build();

        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        tokens.add(and);
        tokens.add(world);
        AST actual = parser.parse(tokens);

        assertEquals(expected,actual);
    }

    @Test
    void parse_two_and (){
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        Token world = Token.builder().value("World").type(Type.PHRASE).build();
        Token and = Token.builder().value("AND").type(Type.AND).build();

        AST expected =
                BinOperator.builder()
                        .leftChild(
                                BinOperator.builder()
                                        .leftChild(Phrase.builder().value(hello).build())
                                        .rightChild(Phrase.builder().value(world).build())
                                        .value(and)
                                        .build()
                                )
                        .value(and)
                        .rightChild(Phrase.builder().value(world).build())
                        .build();

        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        tokens.add(and);
        tokens.add(world);
        tokens.add(and);
        tokens.add(world);

        AST actual = parser.parse(tokens);

        assertEquals(expected,actual);
    }
}