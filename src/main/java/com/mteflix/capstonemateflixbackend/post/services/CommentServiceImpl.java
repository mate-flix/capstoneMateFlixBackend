package com.mteflix.capstonemateflixbackend.post.services;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.CommentRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.CommentResponse;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.Comment;
import com.mteflix.capstonemateflixbackend.post.data.repository.ApartmentRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.CommentRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.UserRepository;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private ApartmentRepository apartmentRepository;
    private CommentRepository commentRepository;
    private PostService postService;
    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
        Apartment apartment = postService.findPostById(commentRequest.getHouseId());
        Comment comment = Comment.builder()
                .message(commentRequest.getMessage())
                .build();
        apartment.setComment(comment);
        commentRepository.save(comment);
        return new CommentResponse("Commented successfully");
    }

    @Override
    public CommentResponse findCommentById(CommentRequest commentRequest) {
        Optional<Comment> foundComment = commentRepository.findById(commentRequest.getId());
        if (foundComment.isEmpty()){
            throw new PostException("Comment not found");
        }
        commentRepository.save(foundComment.get());
        return new CommentResponse("Comment found");
    }

    @Override
    public CommentResponse editComment(CommentRequest commentRequest) {
        Optional<Comment> foundComment = commentRepository.findById(commentRequest.getId());
        if (foundComment.isEmpty()){
            throw new PostException("Comment not found");
        }
        Comment comment = foundComment.get();
        comment.setMessage(commentRequest.getMessage());
        commentRepository.save(comment);
        return new CommentResponse("Comment updated successfully");
    }

    @Override
    public CommentResponse deleteComment(CommentRequest commentRequest) {
        Optional<Comment> foundComment = commentRepository.findById(commentRequest.getId());
        if (foundComment.isEmpty()){
            throw new PostException("Comment not found");
        }
        commentRepository.delete(foundComment.get());
        return new CommentResponse("Comment deleted");
    }
}
