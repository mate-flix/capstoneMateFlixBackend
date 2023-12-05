package com.mteflix.capstonemateflixbackend.post.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {
    private String message;
    private Long houseId;
    private Long id;
}
