package com.mteflix.capstonemateflixbackend.post.services;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.Details;
import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.Post;
import com.mteflix.capstonemateflixbackend.post.data.model.User;
import com.mteflix.capstonemateflixbackend.post.data.repository.PostRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.UserRepository;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import com.mteflix.capstonemateflixbackend.post.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final UserRepository userRepository;
    private final Utils utils;
    @Override
    public PostResponse uploadPostWithPhoto(PostRequest postRequest) throws IOException {
        Optional<User> userId = userRepository.findById(postRequest.getDetails().getUserId());

        if (userId.isEmpty()){
            throw new PostException("User with id "+postRequest.getDetails().getUserId()+" " +
                    "not found");
        }
        Post post = utils.uploadWithPhoto(postRequest);
        return new PostResponse("Uploaded");
    }
}
