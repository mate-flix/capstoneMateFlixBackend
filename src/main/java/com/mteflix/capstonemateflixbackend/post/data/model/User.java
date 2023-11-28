package com.mteflix.capstonemateflixbackend.post.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @OneToOne
    private Profile profile;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
