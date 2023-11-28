package com.mteflix.capstonemateflixbackend.post.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private String name;
    private String address;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
