package com.yevgeniy.lexers;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Token {

    Type type;
    String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type && value.equals(token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
