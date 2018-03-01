package com.courses.tellus.autosalon.utils;

public enum AutoUtil {

    FIRST_STATEMENT(1),
    SECOND_STATEMENT(2),
    THIRD_STATEMENT(3),
    FOURTH_STATEMENT(4),
    FIFTH_STATEMENT(5),
    SIXTH_STATEMENT(6);

    private final int statement;

    AutoUtil(final int statement) {
        this.statement = statement;
    }

    public int getStatement() {
        return statement;
    }
}
