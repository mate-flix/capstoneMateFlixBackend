package com.mteflix.capstonemateflixbackend.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostException extends RuntimeException {
        private static final long statusCode = 400L;
        public PostException(String message) {
            super(message);
        }
    }
