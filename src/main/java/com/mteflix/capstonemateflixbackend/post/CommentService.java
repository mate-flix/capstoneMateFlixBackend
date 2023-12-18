package com.mteflix.capstonemateflixbackend.post;


import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;

public interface CommentService {
    ApiResponse<?> addComment(Long mediaId, AddCommentRequest request) throws MateFlixException;

    ApiResponse<?> updateComment(Long commentId, Long userId, UpdateCommentRequest request) throws MateFlixException;
}
