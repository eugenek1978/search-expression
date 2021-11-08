package com.yevgeniy.parsers;

import com.yevgeniy.lexers.Lexer;
import com.yevgeniy.lexers.Token;
import com.yevgeniy.parsers.Parser;
import com.yevgeniy.parsers.ast.AST;

import java.util.List;

public class SearchParser implements Parser {
    public SearchParser(Lexer searchLexer) {
    }

    @Override
    public AST parse(List<Token> tokens) {
        return null;
    }
}
