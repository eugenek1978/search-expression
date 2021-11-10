package com.yevgeniy.parsers.ast;

import com.yevgeniy.lexers.Token;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phrase implements AST {
    private Token value;
}
