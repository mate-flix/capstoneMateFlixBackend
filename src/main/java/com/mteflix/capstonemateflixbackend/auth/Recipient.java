package com.mteflix.capstonemateflixbackend.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Recipient {
    private String email;
    private String name;
}
