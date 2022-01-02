package com.edkarmanov.firstapigradle.exceptions;

public class NoSuchItemException extends RuntimeException{
    public NoSuchItemException(String message) {
        super(message);
    }
}
