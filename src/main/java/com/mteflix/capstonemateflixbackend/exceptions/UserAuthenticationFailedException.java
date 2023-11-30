package com.mteflix.capstonemateflixbackend.exceptions;

public class UserAuthenticationFailedException extends RuntimeException {
    public UserAuthenticationFailedException(String message) {
        super(message);
    }

}
