package com.mteflix.capstonemateflixbackend.post.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class CommentResponse {
    private String message;
    public CommentResponse(String message){
        this.message = message;
    }
}
