package com.mteflix.capstonemateflixbackend.auth;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.user.User;

import java.util.List;

public interface AuthService {
    AuthResponse register(AuthRequest registerRequest);
    boolean verifyUserEmailByLink(String verificationCode);
    boolean verifyUserEmailByCode(String email, String verificationCode);
    User getUserById(Long id) throws MateFlixException;

    AuthResponse getUserBy(Long id) throws MateFlixException;

    List<AuthResponse> getUsers(int page, int size);

    User getUserBy(String username);
}
