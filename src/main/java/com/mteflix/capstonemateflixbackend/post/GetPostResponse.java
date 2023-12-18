package com.mteflix.capstonemateflixbackend.post;

import com.mteflix.capstonemateflixbackend.user.User;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class GetPostResponse {
    private String title;
    private String description;
    private String url;

    private Long uploaderId;
    private LocalDateTime createdAt;

}
