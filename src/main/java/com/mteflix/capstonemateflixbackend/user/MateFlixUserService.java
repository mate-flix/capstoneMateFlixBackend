package com.mteflix.capstonemateflixbackend.user;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class MateFlixUserService implements UserService{
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User getUserById(Long id) throws MateFlixException {
        return userRepository.findById(id).orElseThrow(
                ()-> new MateFlixException(String.format("user with id %d not found", id))
        );
    }
    @Override
    public UserResponse getUserBy(Long id) throws MateFlixException {
        User user = getUserById(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        log.info("users:: {}", users);
        return users.stream()
                    .map(user->modelMapper.map(user, UserResponse.class))
                    .toList();

    }

    @Override
    public User getUserBy(String email)  {
        return userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(
                        String.format("user with email %s not found", email)
                ));
    }
}
