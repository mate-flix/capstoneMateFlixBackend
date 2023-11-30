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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private Long houseNumber;
    private String street;
    private String state;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
