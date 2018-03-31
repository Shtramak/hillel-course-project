package com.courses.tellus.exception.jdbc;

public class DatabaseConnectionException extends Exception {

    private String message;
    private int errorCode;

    public DatabaseConnectionException() {
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
        this.message = "Can't get connection to Database";
        this.errorCode = -1;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
