package com.mteflix.capstonemateflixbackend.post.data.repository;

import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
