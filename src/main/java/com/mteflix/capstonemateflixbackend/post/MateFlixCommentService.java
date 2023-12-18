package com.mteflix.capstonemateflixbackend.post;
import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.user.User;
import com.mteflix.capstonemateflixbackend.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MateFlixCommentService implements CommentService{
    private final PostService postService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CommentRepository commentRepository;
    @Override
    public ApiResponse<?> addComment(Long id, AddCommentRequest request) throws MateFlixException {
        Post foundMedia = postService.getPostById(id);
        Comment comment = modelMapper.map(request, Comment.class);
        comment.setMedia(foundMedia);
        User user =userService.getUserById(request.getCommenter());
        comment.setCommenter(user);
        commentRepository.save(comment);
        ApiResponse<?> response = new ApiResponse<>();
        response.setMessage("Comment added successfully");
        return response;
    }

    @Override
    public ApiResponse<?> updateComment(Long commentId, Long userId, UpdateCommentRequest request) throws MateFlixException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Comment with id %d not found",commentId)));

        Long posterId = comment.getCommenter().getId();
        if (!posterId.equals(userId)) throw new MateFlixException("only original comment poster allowed to update comment");

        comment.setText(request.getText());
        commentRepository.save(comment);

        ApiResponse<?> response =  new ApiResponse<>();
        response.setMessage("Comment updated successfully");
        return response;
    }
}
