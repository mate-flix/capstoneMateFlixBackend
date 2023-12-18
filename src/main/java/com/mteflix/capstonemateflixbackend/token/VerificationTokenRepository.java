package com.mteflix.capstonemateflixbackend.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sampson Alfred
 */

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findVerificationTokensByToken(String token);
}
