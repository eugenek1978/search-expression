package com.yevgeniy.interpreters;

import com.yevgeniy.lexers.Lexer;
import com.yevgeniy.lexers.SearchLexer;
import com.yevgeniy.parsers.Parser;
import com.yevgeniy.parsers.SearchParser;

public class SearchProcessor {

    public void process (String searchString) {
        Lexer searchLexer = new SearchLexer();
        Parser searchParser = new SearchParser(searchLexer);
    }
}
