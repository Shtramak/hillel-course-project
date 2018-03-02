package com.courses.tellus.autosalon.dao;

public enum CustomerColumns {
    NAME(1),
    SURNAME(2),
    BIRTHDAY(3),
    PHONE_NUMBER(4),
    AVAILABLE_FUNDS(5),
    ID(6);

    private final int index;

    CustomerColumns(final int index) {
        this.index = index;
    }

    /**
     * Returns index of particular column in table Customer.
     *
     * @return index of column
     */
    public int index() {
        return index;
    }
}
