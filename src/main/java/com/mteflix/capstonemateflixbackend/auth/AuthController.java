package com.mteflix.capstonemateflixbackend.auth;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;

import com.mteflix.capstonemateflixbackend.token.VerificationTokenRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import static java.rmi.server.LogStream.log;
import static org.hibernate.internal.CoreLogging.logger;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) throws MateFlixException {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
    }



    @GetMapping("/verifyEmail")
    public ValidateResponse verifyEmail(@RequestParam("token") String token) throws MateFlixException {
return authService.verify(token);
    }

}
