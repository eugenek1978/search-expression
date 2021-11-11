package com.yevgeniy.parsers;

import com.yevgeniy.lexers.Token;
import com.yevgeniy.parsers.ast.AST;

import java.util.List;

public interface Parser {
    AST parse (List<Token> tokens) throws Exception;

}
