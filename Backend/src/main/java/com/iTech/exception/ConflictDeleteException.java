package com.iTech.exception;

public class ConflictDeleteException extends RuntimeException {
    public ConflictDeleteException(String message){
        super(message);
    }
}
