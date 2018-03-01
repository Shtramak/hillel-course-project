package com.courses.tellus.dao;

public enum OrderUtil {

    FIRST_STATEMENT(1),
    SECOND_STATEMENT(2),
    THIRD_STATEMENT(3),
    FOURTH_STATEMENT(4),
    FIFTH_STATEMENT(5);

    private final int order;

    OrderUtil(final int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
