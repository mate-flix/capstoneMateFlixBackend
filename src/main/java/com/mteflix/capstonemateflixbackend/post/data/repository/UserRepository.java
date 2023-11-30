package com.mteflix.capstonemateflixbackend.post.data.repository;

import com.mteflix.capstonemateflixbackend.post.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
