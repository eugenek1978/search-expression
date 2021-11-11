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
    void parse_phrase() throws Exception {
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        AST expected = Phrase.builder().value(hello).build();
        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        AST actual = parser.parse(tokens);
        assertEquals(expected, actual);
    }

    @Test
    void parse_one_and() throws Exception {
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

        assertEquals(expected, actual);
    }

    @Test
    void parse_Phrase_AND_Phrase_AND_Phrase() throws Exception {
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

        assertEquals(expected, actual);
    }

    @Test
    void parse_Phrase_AND_Phrase_OR_Phrase() throws Exception {
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        Token world = Token.builder().value("World").type(Type.PHRASE).build();
        Token and = Token.builder().value("AND").type(Type.AND).build();
        Token or = Token.builder().value("OR").type(Type.OR).build();

        AST expected =
                BinOperator.builder()
                        .leftChild(
                                BinOperator.builder()
                                        .leftChild(Phrase.builder().value(hello).build())
                                        .rightChild(Phrase.builder().value(world).build())
                                        .value(and)
                                        .build()
                        )
                        .value(or)
                        .rightChild(Phrase.builder().value(world).build())
                        .build();

        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        tokens.add(and);
        tokens.add(world);
        tokens.add(or);
        tokens.add(world);

        AST actual = parser.parse(tokens);

        assertEquals(expected, actual);
    }

    @Test
    void parse_Phrase_OR_Phrase_AND_Phrase() throws Exception {
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        Token world = Token.builder().value("World").type(Type.PHRASE).build();
        Token and = Token.builder().value("AND").type(Type.AND).build();
        Token or = Token.builder().value("OR").type(Type.OR).build();

        AST expected =
                BinOperator.builder()
                        .leftChild(
                                Phrase.builder().value(hello).build()
                        )
                        .value(or)
                        .rightChild(
                                BinOperator.builder()
                                        .leftChild(Phrase.builder().value(world).build())
                                        .rightChild(Phrase.builder().value(world).build())
                                        .value(and)
                                        .build())
                        .build();

        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        tokens.add(or);
        tokens.add(world);
        tokens.add(and);
        tokens.add(world);

        AST actual = parser.parse(tokens);

        assertEquals(expected, actual);
    }

    @Test
    void parse_Hello_OR_World() throws Exception {
        Parser parser = new SearchParser();
        Token hello = Token.builder().value("Hello").type(Type.PHRASE).build();
        Token world = Token.builder().value("World").type(Type.PHRASE).build();
        Token or = Token.builder().value("OR").type(Type.OR).build();

        AST expected =
                BinOperator.builder()
                        .leftChild(Phrase.builder().value(hello).build())
                        .value(or)
                        .rightChild(Phrase.builder().value(world).build())
                        .build();

        List<Token> tokens = new ArrayList<>();
        tokens.add(hello);
        tokens.add(or);
        tokens.add(world);

        AST actual = parser.parse(tokens);

        assertEquals(expected, actual);
    }

    @Test
    void parse_AND_OR_invalid_syntax_exception() throws Exception {
        Parser parser = new SearchParser();
        Token and = Token.builder().value("AND").type(Type.AND).build();
        Token or = Token.builder().value("OR").type(Type.OR).build();


        List<Token> tokens = new ArrayList<>();
        tokens.add(and);
        tokens.add(or);

        assertThrows(Exception.class,()-> parser.parse(tokens));
    }
}