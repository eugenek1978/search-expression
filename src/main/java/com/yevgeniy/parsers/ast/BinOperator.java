package com.yevgeniy.parsers.ast;

import com.yevgeniy.lexers.Token;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BinOperator implements AST {
    private AST leftChild;
    private AST rightChild;
    private Token value;
}
