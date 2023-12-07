package com.mteflix.capstonemateflixbackend.user;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;

import java.util.List;

public interface UserService {

    User getUserById(Long id) throws MateFlixException;

    UserResponse getUserBy(Long id) throws MateFlixException;

    List<UserResponse> getUsers(int page, int size);

    User getUserBy(String username);
}
