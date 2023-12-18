package com.mteflix.capstonemateflixbackend.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResetPasswordRequest {
    private String code;
    private String newPassword;


}
