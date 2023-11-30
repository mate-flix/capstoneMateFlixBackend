package com.mteflix.capstonemateflixbackend.post.data.repository;

import com.mteflix.capstonemateflixbackend.post.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByStateAndStreetAndHouseNumber(String state, String street, Long houseNumber);
}
