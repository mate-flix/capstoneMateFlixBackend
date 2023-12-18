package com.mteflix.capstonemateflixbackend.profile;

import com.mteflix.capstonemateflixbackend.School.School;
import com.mteflix.capstonemateflixbackend.contactInformation.ContactInformation;
import com.mteflix.capstonemateflixbackend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String course;
    @OneToOne
    private School school;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne
    private ContactInformation contactInformation;
    private String profilePicture;
    @OneToOne
    private User user;
}
