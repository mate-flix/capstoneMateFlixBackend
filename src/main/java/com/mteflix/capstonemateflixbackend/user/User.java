package com.mteflix.capstonemateflixbackend.user;

import com.mteflix.capstonemateflixbackend.notification.Notification;
import com.mteflix.capstonemateflixbackend.profile.Profile;

import com.mteflix.capstonemateflixbackend.token.VerificationToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

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
    @OneToOne(cascade = CascadeType.ALL)
    private VerificationToken token;
    private boolean isEnabled = false;
    @OneToMany
    private Set<User> friends;

    @OneToMany
    private Set<User> friendRequest;
    @OneToMany
    private Set<User> recievedFriendRequest;
    @OneToMany
    private List<Notification> notifications;

}
