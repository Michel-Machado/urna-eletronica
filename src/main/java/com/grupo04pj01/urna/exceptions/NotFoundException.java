package com.grupo04pj01.urna.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(String.format(message));
    }
}
