package com.mteflix.capstonemateflixbackend.post.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Details {
    private String title;
    private Long houseNumber;
    private String street;
    private String state;
    private String houseType;
    private String houseDescription;
    private String mainDescription;
    private MultipartFile multipartFile;
    private Long houseId;
    private Long userId;
    private String photoUrl;
    private Long postId;
}
