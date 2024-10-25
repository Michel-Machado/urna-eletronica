package com.grupo04pj01.urna.exceptions;

public class EleitorCadastradoException extends RuntimeException{
    public EleitorCadastradoException(String message) {
        super(String.format(message));
    }
}
