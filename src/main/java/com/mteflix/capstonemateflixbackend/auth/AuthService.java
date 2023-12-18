package com.mteflix.capstonemateflixbackend.auth;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.user.User;

import java.util.List;

public interface AuthService {
    AuthResponse register(AuthRequest registerRequest) throws MateFlixException;

    ValidateResponse verify(String token) throws MateFlixException;

}
