package com.yevgeniy.parsers;

import com.yevgeniy.lexers.Token;
import com.yevgeniy.lexers.Type;
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
    public AST parse(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        return orExpression();
    }

    private AST orExpression() throws Exception {
        AST node = andExpression();
        while (position < tokens.size() && tokens.get(position).getType() == Type.OR){
            Token operator = eat(Type.OR);
            node =
                    BinOperator.builder()
                            .leftChild(node)
                            .rightChild(andExpression())
                            .value(operator)
                            .build();
        }
        return node;
    }

    private AST andExpression() throws Exception {
        AST node = phrase();

        while (position < tokens.size() && tokens.get(position).getType() == Type.AND){
            Token operator = eat(Type.AND);
            node =
                    BinOperator.builder()
                            .leftChild(node)
                            .rightChild(phrase())
                            .value(operator)
                            .build();
        }

        return node;

    }

    private Token eat(Type type) throws Exception {
        if(tokens.get(position).getType() != type){
            throw new Exception();
        }
        return tokens.get(position++);
    }

    private AST phrase() throws Exception {

            if  (tokens.get(position).getType() == Type.LPAREN) {
                eat(Type.LPAREN);
                AST node = orExpression();
                eat(Type.RPAREN);
                return node;
            }
            else if  (tokens.get(position).getType() == Type.PHRASE) {
                return Phrase.builder()
                        .value(
                                eat(Type.PHRASE)
                        )
                        .build();
            }
            else {
                throw new Exception();
            }

    }
}
