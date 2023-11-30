package com.mteflix.capstonemateflixbackend.post.controller;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import com.mteflix.capstonemateflixbackend.post.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/upload-post")
    public ResponseEntity<?> uploadPost(@RequestBody PostRequest postRequest) throws IOException {
        PostResponse response = postService.uploadPostWithPhoto(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/edit-post/{post-id}")
    public ResponseEntity<?> editPost(@RequestBody PostRequest postRequest) throws IOException {
        PostResponse response = postService.editPostWithPhoto(postRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/delete-post.{post-id}")
    public ResponseEntity<?> deletePost(@RequestBody PostRequest postRequest){
        PostResponse response = postService.deletePost(postRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}