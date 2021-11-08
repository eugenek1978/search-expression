package com.yevgeniy.lexers;

import java.util.ArrayList;
import java.util.List;

import static com.yevgeniy.lexers.Type.*;

public class SearchLexer implements Lexer {
    private String searchString;
    private char currentChar;
    private int position;

    public SearchLexer() {
        this.position = 0;
    }

    @Override
    public Token getNextToken() throws Exception {

        while (currentChar != '\0') {
            if (Character.isSpaceChar(currentChar)){
                skipWhiteSpace();
                continue;
            }

            if (Character.isAlphabetic(currentChar)){
                return reservedWord();
            }

            if (currentChar == '('){
                advance();
                return leftParentheses();
            }

            if (currentChar == ')'){
                advance();
                return rightParentheses();
            }

            if (currentChar == '\'') {
                advance();
                return new Token(PHRASE, phrase());
            }

            throw new Exception("Invalid character");
        }

        return new Token(EOS,"\0");
    }

    @Override
    public List<Token> tokenize(String text) throws Exception {
        List<Token> tokens = new ArrayList<>();
        searchString = text;
        currentChar = searchString.charAt(0);
        Token currentToken = getNextToken();
        while (currentToken.getType() != EOS )
        {
            tokens.add(currentToken);
            currentToken = getNextToken();
        }

        return tokens;
    }

    private Token rightParentheses() {
        return new Token(RPAREN, ")");
    }

    private Token leftParentheses() {
        return new Token(LPAREN, "(");
    }

    private Token reservedWord() {
        StringBuilder sb = new StringBuilder();
        while(Character.isAlphabetic(currentChar)){
            sb.append(currentChar);
            advance();
        }

        Type type = valueOf(sb.toString());

        return new Token(type, sb.toString());
    }

    private void skipWhiteSpace() {
        while (currentChar != '\0' && Character.isSpaceChar(currentChar))
            advance();
    }

    private void advance() {
        position++;
        if (position == searchString.length()) {
            currentChar = '\0';
        } else {
            currentChar = searchString.charAt(position);
        }
    }

    private String phrase() {
        StringBuilder sb = new StringBuilder();
        while (currentChar != '\'') {
            sb.append(currentChar);
            advance();
        }
        advance();
        return sb.toString();
    }
}
