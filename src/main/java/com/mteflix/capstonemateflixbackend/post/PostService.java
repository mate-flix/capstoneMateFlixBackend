package com.mteflix.capstonemateflixbackend.post;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.exceptions.MediaUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    UploadPostResponse upload( String creatorEmail, String title, String description, MultipartFile image) throws MateFlixException;
    Post getPostById(Long id) throws MateFlixException;





    GetPostResponse getPostBy(Long id) throws MateFlixException;

    List<GetPostResponse> getUsers(int page, int size);


}
