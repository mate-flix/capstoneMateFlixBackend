package com.mteflix.capstonemateflixbackend.post.services;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;

import java.io.IOException;

public interface PostService {
    PostResponse uploadPostWithPhoto(PostRequest postRequest) throws IOException;
    PostResponse editPostWithPhoto(PostRequest postRequest) throws IOException;
    PostResponse deletePost(PostRequest postRequest);
}
