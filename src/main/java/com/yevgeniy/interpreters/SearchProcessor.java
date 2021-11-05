package com.yevgeniy.interpreters;

import com.yevgeniy.lexers.Lexer;
import com.yevgeniy.lexers.SearchLexer;
import com.yevgeniy.parsers.Parser;
import com.yevgeniy.parsers.SearchParser;

public class SearchProcessor {

    public void process (String searchString) {
        Lexer searchLexer = new SearchLexer(searchString);
        Parser searchParser = new SearchParser(searchLexer);
        Interpreter elasticInterpreter = new ElasticInterpreter(searchParser);
        elasticInterpreter.interpret();
    }
}
