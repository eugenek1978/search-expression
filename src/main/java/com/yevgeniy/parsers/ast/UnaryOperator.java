package com.yevgeniy.parsers.ast;

import com.yevgeniy.lexers.Token;

public class UnaryOperator implements AST {
    private Token value;
    private AST child;
}
