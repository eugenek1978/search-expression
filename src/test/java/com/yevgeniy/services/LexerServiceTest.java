package com.yevgeniy.services;

import com.yevgeniy.lexers.Token;
import com.yevgeniy.lexers.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LexerServiceTest {

    @Autowired
    LexerService lexerService;

    @Test
    void multipleLexerServiceCalls () throws Exception {
        List<List<Token>> actualResult = new ArrayList();
        List<List<Token>> expectedResult = new ArrayList();
        for (int i = 0; i < 100; i++) {
            actualResult.add(lexerService.tokenize("'Hello'"));
            expectedResult.add(List.of(Token.builder().type(Type.PHRASE).value("Hello").build()));
        }

       assertEquals(expectedResult,actualResult);
    }


}