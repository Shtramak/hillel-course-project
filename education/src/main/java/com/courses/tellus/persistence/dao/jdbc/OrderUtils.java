package com.courses.tellus.persistence.dao.jdbc;

public enum OrderUtils {

    FIRST_STATEMENT(1),
    SECOND_STATEMENT(2),
    THIRD_STATEMENT(3),
    FOURTH_STATEMENT(4),
    FIFTH_STATEMENT(5);

    private final int order;

    OrderUtils(final int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}