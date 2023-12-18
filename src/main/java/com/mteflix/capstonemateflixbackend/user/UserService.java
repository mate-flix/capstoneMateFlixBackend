package com.mteflix.capstonemateflixbackend.user;

import com.mteflix.capstonemateflixbackend.auth.AuthRequest;
import com.mteflix.capstonemateflixbackend.auth.AuthResponse;
import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.globalDTO.Response;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User getUserById(Long id) throws MateFlixException;

    UserResponse getUserBy(Long id) throws MateFlixException;

    List<UserResponse> getUsers(int page, int size);

    Optional<User> getUserBy(String username);
    User createUser (CreateUserRequest createUserRequest) throws MateFlixException;
    User saveUser (User user);
    Response sendFriendRequest (FriendRequest friendRequest) throws MateFlixException;
    Response acceptFriendRequest (FriendRequest friendRequest) throws MateFlixException;

}
