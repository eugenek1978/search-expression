package com.yevgeniy.parsers.ast;

import com.yevgeniy.lexers.Token;

public class BinOperator implements AST {
    private AST leftChild;
    private AST rightChild;
    private Token value;
}
