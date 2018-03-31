package com.courses.tellus.exception.jdbc;

public class EntityIdNotFoundException extends Exception {


    private String msg;
    private int errorCode;

    public EntityIdNotFoundException() {
        super();
        this.msg = "Entity not found by selected id";
        this.errorCode = -2;
    }

    public String getMsg() {
        return msg;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
