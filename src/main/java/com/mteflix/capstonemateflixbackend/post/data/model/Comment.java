package com.mteflix.capstonemateflixbackend.post.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    private String message;
    @OneToOne
    private Apartment apartment;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
