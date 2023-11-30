package com.mteflix.capstonemateflixbackend.auth;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MetaFlixAuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    public void registerTest () throws MateFlixException {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("jkfjk");
        authRequest.setEmail("timileyin1708@gmail.com");
        authRequest.setFirstName("tunde");
     AuthResponse authResponse =   authService.register(authRequest);

        assertNotNull(authResponse);
    }
}