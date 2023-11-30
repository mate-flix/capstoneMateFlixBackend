package com.mteflix.capstonemateflixbackend.post.services;


import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.Post;
import com.mteflix.capstonemateflixbackend.post.data.repository.ApartmentRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.PostRepository;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import com.mteflix.capstonemateflixbackend.post.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final ApartmentRepository apartmentRepository;
    private final CloudService cloudService;
    private final PostRepository postRepository;
    private final Utils utils;
    @Override
    public PostResponse uploadPostWithPhoto(PostRequest postRequest) throws IOException {
        utils.uploadWithPhoto(postRequest);
        return new PostResponse("Apartment Uploaded");
    }

    @Override
    public PostResponse editPostWithPhoto(PostRequest postRequest) throws IOException {
        Optional<Apartment> foundApartment = apartmentRepository.findApartmentByHouseNumberAndId(
                postRequest.getDetails().getHouseNumber(), postRequest.getDetails().getHouseId());
        if (foundApartment.isEmpty()){
            throw new PostException("Apartment with specified house number and ID does not exist");
        }

        Apartment apartment = foundApartment.get();
        apartment.setDescription(postRequest.getDetails().getHouseDescription());
        apartment.setHouseType(postRequest.getDetails().getHouseType());

        Optional<Post> foundPost = postRepository.findById(postRequest.getDetails().getPostId());
        if (foundPost.isEmpty()){
            throw new PostException("Post with specified ID not found");
        }
        Post post = foundPost.get();
        post.setApartment(apartment);
        post.setTitle(postRequest.getDetails().getTitle());
        post.setDescription(postRequest.getDetails().getMainDescription());
        post.setDateUploaded(LocalDateTime.now());
        if (postRequest.getDetails().getMultipartFile() != null) {
            String newImageUrl = cloudService.upload(postRequest.getDetails().getMultipartFile());
            post.setPhotoUrl(newImageUrl);
        }
        return new PostResponse("Apartment updated successfully");
    }


}

