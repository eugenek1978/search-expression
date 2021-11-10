package com.yevgeniy.parsers;

import com.yevgeniy.lexers.Lexer;
import com.yevgeniy.lexers.Token;
import com.yevgeniy.lexers.Type;
import com.yevgeniy.parsers.Parser;
import com.yevgeniy.parsers.ast.AST;
import com.yevgeniy.parsers.ast.BinOperator;
import com.yevgeniy.parsers.ast.Phrase;

import java.util.List;

public class SearchParser implements Parser {
    int position;
    List<Token> tokens;
    public SearchParser() {
        position = 0;

    }

    @Override
    public AST parse(List<Token> tokens) {
        this.tokens = tokens;
        return and_expression();
    }

    private AST and_expression() {
        AST node = phrase();

        while (position < tokens.size() && tokens.get(position).getType() == Type.AND){
            Token operator = tokens.get(position++);
            node =
                    BinOperator.builder()
                            .leftChild(node)
                            .rightChild(phrase())
                            .value(operator)
                            .build();
        }

        return node;

    }

    private AST phrase() {
        return Phrase.builder().value(tokens.get(position++)).build();
    }
}
