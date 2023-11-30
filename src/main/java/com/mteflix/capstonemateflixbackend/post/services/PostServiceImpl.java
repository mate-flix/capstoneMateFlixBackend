package com.mteflix.capstonemateflixbackend.post.services;


import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.repository.ApartmentRepository;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import com.mteflix.capstonemateflixbackend.post.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final CloudService cloudService;
    private final ApartmentRepository apartmentRepository;
    private final Utils utils;
    @Override
    public PostResponse uploadPostWithPhoto(PostRequest postRequest) throws IOException {
        utils.uploadWithPhoto(postRequest);
        return new PostResponse("Apartment Uploaded");
    }

    @Override
    public PostResponse editPostWithPhoto(PostRequest postRequest) throws IOException {
        Optional<Apartment> foundApartment = apartmentRepository.findById(
                postRequest.getDetails().getHouseId());
        if (foundApartment.isEmpty()){
            throw new PostException("Apartment with specified ID does not exist");
        }

        Optional<Apartment> foundPost = apartmentRepository.findById(postRequest.getDetails().getPostId());
        if (foundPost.isEmpty()){
            throw new PostException("Apartment with specified ID not found");
        }
        Apartment apartment = foundPost.get();
        apartment.setHouseType(postRequest.getDetails().getHouseType());
        apartment.setDescription(postRequest.getDetails().getMainDescription());
        apartment.setDateUploaded(LocalDateTime.now());

        if (postRequest.getDetails().getMultipartFile() != null) {
            String newImageUrl = cloudService.upload(postRequest.getDetails().getMultipartFile());
            apartment.setPhotoUrl(Collections.singleton(newImageUrl));
        }
        return new PostResponse("Apartment updated successfully");
    }

    @Override
    public PostResponse deletePost(PostRequest postRequest) {
        Optional<Apartment> foundPost = apartmentRepository.findById(postRequest.getDetails().getPostId());
        if (foundPost.isEmpty()){
            throw new PostException("Apartment with specified ID not found");
        }
        apartmentRepository.delete(foundPost.get());
        return new PostResponse("Delete successful");
    }
}
