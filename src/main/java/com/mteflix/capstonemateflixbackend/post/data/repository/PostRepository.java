package com.mteflix.capstonemateflixbackend.post.data.repository;

import com.mteflix.capstonemateflixbackend.post.data.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
