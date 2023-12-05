package com.mteflix.capstonemateflixbackend.post.services;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.ViewPostResponse;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import org.springframework.data.domain.AbstractPageRequest;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostResponse uploadPostWithPhoto(PostRequest postRequest) throws IOException;
    PostResponse editPostWithPhoto(PostRequest postRequest) throws IOException;
    PostResponse deletePost(PostRequest postRequest);
    Apartment findPostById(Long id);
    List<ViewPostResponse> findAllPostsByPoster(PostRequest postRequest);
}
