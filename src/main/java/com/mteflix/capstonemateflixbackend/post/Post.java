package com.mteflix.capstonemateflixbackend.post;

import com.mteflix.capstonemateflixbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table (name = "media")
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    @ManyToOne
    private User uploader;
    private LocalDateTime createdAt;


    @PrePersist
    public void setCreatedAt(){
        this.createdAt=LocalDateTime.now();
    }
}
