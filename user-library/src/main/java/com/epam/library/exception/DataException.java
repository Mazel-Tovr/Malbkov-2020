package com.epam.library.exception;

public class DataException extends Exception {
    private int status = 200;
    public DataException(String message) {
        super(message);
    }
    public DataException(String message, int status) { super(message);this.status = status;}
    public int getStatus() {
        return status;
    }
}
