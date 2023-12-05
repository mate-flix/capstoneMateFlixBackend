package com.mteflix.capstonemateflixbackend.post.services;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.CommentRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.CommentResponse;

public interface CommentService {
    CommentResponse addComment(CommentRequest commentRequest);
    CommentResponse  findCommentById(CommentRequest commentRequest);
    CommentResponse editComment(CommentRequest commentRequest);
    CommentResponse deleteComment(CommentRequest commentRequest);
}
