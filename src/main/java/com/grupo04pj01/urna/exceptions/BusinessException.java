package com.grupo04pj01.urna.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(String.format(message));
    }
}
