package com.courses.tellus.exception.jdbc;

public class EntityIdNotFoundException extends Exception {

    private static final String MESSAGE = "Entity not found by selected id";
    private final int errorCode = -1;

    public EntityIdNotFoundException() {
        super();
    }

    public String getMessage() {
        return MESSAGE;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
