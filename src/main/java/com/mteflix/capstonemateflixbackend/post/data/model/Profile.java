package com.mteflix.capstonemateflixbackend.post.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String firstName;
    private String lastName;
    private String course;
    private String userName;
    private String interest;
    private LocalDateTime timestamp;
    @OneToOne
    private School school;
    @OneToOne
    private Address address;
    @OneToOne
    private ContactInformation contactInformation;
    private String profilePictureUrl;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
