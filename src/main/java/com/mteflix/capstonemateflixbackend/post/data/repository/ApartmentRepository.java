package com.mteflix.capstonemateflixbackend.post.data.repository;

import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findApartmentByHouseNumberAndId(Long houseNumber, Long houseId);

    Optional<List<Apartment>> findByUser(User user);
}
