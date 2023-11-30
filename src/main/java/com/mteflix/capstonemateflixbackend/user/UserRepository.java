package com.mteflix.capstonemateflixbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndToken(String email, String verificationCode);

    Optional<User> findByToken(String verificationCode);

    Optional<User> findByEmail(String email);
}
