package com.mteflix.capstonemateflixbackend.post.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Apartment {
    private String houseType;
    private String description;
    @ElementCollection
    private Set<String> photoUrl = new HashSet<>();
    private LocalDateTime dateCreated;
    private LocalDateTime dateUploaded;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @OneToOne
    private Address address;
    @OneToOne
    private User owner;
    @OneToOne
    private Comment comment;
}
