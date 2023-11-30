package com.mteflix.capstonemateflixbackend.post.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewPostResponse {
    private String houseType;
    private String houseDescription;
    private String mainDescription;
    private Long id;

}
