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
public class Post {
    @OneToOne
    private Apartment apartment;
    private String title;
    private String description;
    @OneToOne
    private User uploader;
    private String photoUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUploaded;
    @ElementCollection
    private Set<String> postMedia = new HashSet<>();
    @OneToOne
    private School school;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
}
