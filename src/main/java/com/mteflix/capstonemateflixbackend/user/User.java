package com.mteflix.capstonemateflixbackend.user;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.mteflix.capstonemateflixbackend.profile.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated
    private List<Authority> authorities;
    @OneToOne
    private Profile profile;
    private String token;
    private boolean isVerified;
}
