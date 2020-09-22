package com.tstu.library.exception;

public class UserException extends Exception
{
    private int status = 200;
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
