package com.mteflix.capstonemateflixbackend.auth;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request){
        return ResponseEntity.status(CREATED).body(authService.register(request));
    }
    @GetMapping("/verify/{verificationCode}")
    public ResponseEntity<String> verifyUserEmailByLink(@PathVariable String verificationCode) {
        if (authService.verifyUserEmailByLink(verificationCode)) {
            return ResponseEntity.ok("Email verification successful. You can now log in.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification code or user not found.");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUserEmailByCode(@RequestParam String email, @RequestParam String verificationCode) {
        if (authService.verifyUserEmailByCode(email, verificationCode)) {
            return ResponseEntity.ok("Email verification successful. You can now log in.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification code or user not found.");
        }
    }
    @GetMapping("/user")
    public OAuth2User getUser(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }
}
