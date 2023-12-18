package com.mteflix.capstonemateflixbackend.post;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;

public class ResourceNotFoundException extends MateFlixException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
