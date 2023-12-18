package com.mteflix.capstonemateflixbackend.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VerificationRequest {
    private String token;
    private String email;

}
