package com.courses.tellus.exception.jdbc;

public class DatabaseConnectionException extends Exception {

    private static final String MESSAGE = "Can't get connection to Database";
    private final int errorCode = -1;

    public DatabaseConnectionException() {
    }

    public DatabaseConnectionException(final Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return MESSAGE;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
