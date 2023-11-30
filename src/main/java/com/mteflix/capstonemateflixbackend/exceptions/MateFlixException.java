package com.mteflix.capstonemateflixbackend.exceptions;

public class MateFlixException extends Throwable {
    public MateFlixException(String message){
        super(message);
    }

    public MateFlixException(Throwable throwable){
        super(throwable);
    }
}
