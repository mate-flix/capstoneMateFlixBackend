package com.mteflix.capstonemateflixbackend.post.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    @OneToOne
    private Address address;
    private String houseType;
    private String description;
    @ElementCollection
    private Set<String> imageUrl = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}