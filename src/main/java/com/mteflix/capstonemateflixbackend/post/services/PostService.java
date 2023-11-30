package com.mteflix.capstonemateflixbackend.post.services;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.ViewPostResponse;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostResponse uploadPostWithPhoto(PostRequest postRequest) throws IOException;
    PostResponse editPostWithPhoto(PostRequest postRequest) throws IOException;
    PostResponse deletePost(PostRequest postRequest);
    List<ViewPostResponse> findAllPostsByPoster(PostRequest postRequest);
}
