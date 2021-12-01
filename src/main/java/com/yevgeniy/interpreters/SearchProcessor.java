package com.yevgeniy.interpreters;

import com.yevgeniy.interpreters.visitor.Visitor;
import com.yevgeniy.interpreters.visitor.VisitorBuilder;
import com.yevgeniy.parsers.ast.AST;
import com.yevgeniy.parsers.ast.BinOperator;
import com.yevgeniy.parsers.ast.UnaryOperator;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class SearchProcessor {

    private Visitor visitor;

    SearchProcessor() {
        Function<UnaryOperator, String> visitUnaryOperator = this::processUnaryOperator;
        Function<BinOperator, String> visitBinaryOperator = this::processBinaryOperator;
        Consumer<VisitorBuilder<String>> repository =
                Visitor.<UnaryOperator, String>forType(UnaryOperator.class)
                        .execute(visitUnaryOperator)
                        .forType(BinOperator.class).execute(visitBinaryOperator);

        visitor = Visitor.of(repository);
    }

    private String processBinaryOperator(BinOperator binOperator) {
        return null;
    }

    private String processUnaryOperator(UnaryOperator unaryOperator) {
        return null;
    }

    public void process(AST ast) {
        visitor.visit(ast);
    }
}
