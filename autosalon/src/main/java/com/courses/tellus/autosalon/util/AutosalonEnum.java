package com.courses.tellus.autosalon.util;

public enum AutosalonEnum {

    FIRST_ENUM(1),
    SECOND_ENUM(2),
    THIRD_ENUM(3),
    FOURTH_ENUM(4);

    private final int statement;

    AutosalonEnum(final int statement) {
        this.statement = statement;
    }

    public int getStatement() {
        return statement;
    }
}
