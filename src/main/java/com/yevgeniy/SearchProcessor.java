package com.yevgeniy;

public class SearchProcessor {

    public void process (String searchString) {
        Lexer searchLexer = new SearchLexer(searchString);
        Parser searchParser = new SearchParser(searchLexer);
        Interpreter elasticInterpreter = new ElasticInterpreter(searchParser);
        elasticInterpreter.interpret();
    }
}
