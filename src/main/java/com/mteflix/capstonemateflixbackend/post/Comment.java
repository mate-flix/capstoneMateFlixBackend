package com.mteflix.capstonemateflixbackend.post;


import com.mteflix.capstonemateflixbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User commenter;
    @ManyToOne
    private Post media;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void setUpdatedAt(){
        updatedAt=LocalDateTime.now();
    }
}
