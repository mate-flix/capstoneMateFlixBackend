package com.mteflix.capstonemateflixbackend.post.controller;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.CommentRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.CommentResponse;
import com.mteflix.capstonemateflixbackend.post.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/add-comment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest){
        CommentResponse addComment = commentService.addComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addComment);
    }
    @GetMapping("/find-comment/{comment-id}")
    public ResponseEntity<?> findComment(@RequestBody CommentRequest commentRequest){
        CommentResponse foundComment = commentService.findCommentById(commentRequest);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundComment);
    }
    @PutMapping("/edit-comment/{comment-id}")
    public ResponseEntity<?> editComment(@RequestBody CommentRequest commentRequest){
        CommentResponse edited = commentService.editComment(commentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(edited);
    }
    @DeleteMapping("delete-comment/{comment-id}")
    public ResponseEntity<?> deleteComment(@RequestBody CommentRequest commentRequest){
        CommentResponse deleted = commentService.deleteComment(commentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
