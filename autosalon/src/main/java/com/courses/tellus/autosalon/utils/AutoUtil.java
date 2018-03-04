package com.courses.tellus.autosalon.utils;

public enum AutoUtil {
    ID_AUTO(1),
    AUTO_BRAND(1),
    AUTO_MODEL(2),
    MANUFACT_YEAR(3),
    COUNTRY(4),
    PRICE(5),
    ID(6);


    private final int index;

    AutoUtil(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
