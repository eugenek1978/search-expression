package com.yevgeniy.services;

import com.yevgeniy.lexers.Token;
import com.yevgeniy.lexers.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LexerServiceTest {


    @Autowired
    LexerService lexerService;

    @Test
    void multipleLexerServiceCalls() {
        List<List<Token>> expectedResult = new ArrayList();
        int size = 100000;

        List<List<Token>> actualResult = IntStream.range(0, size).parallel()
                .mapToObj((int i) -> {
                    List<Token> result;
                    try {
                        result = lexerService.tokenize("'Hello'");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    return result;
                })
                .collect(Collectors.toList());

        for (int i = 0; i < size; i++) {
            expectedResult.add(List.of(Token.builder().type(Type.PHRASE).value("Hello").build()));
        }

        assertEquals(expectedResult, actualResult);
    }


}