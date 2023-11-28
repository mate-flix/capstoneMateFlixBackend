package com.mteflix.capstonemateflixbackend.post.utils;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.Details;
import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.dto.response.PostResponse;
import com.mteflix.capstonemateflixbackend.post.data.model.Address;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.Post;
import com.mteflix.capstonemateflixbackend.post.data.model.User;
import com.mteflix.capstonemateflixbackend.post.data.repository.AddressRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.PostRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.UserRepository;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import com.mteflix.capstonemateflixbackend.post.services.CloudService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ProtocolException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Utils {
    private final CloudService cloudService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    public Post uploadWithPhoto(PostRequest postRequest) throws IOException {
            PostResponse response = new PostResponse();
            Details details = postRequest.getDetails();

            Address address = Address.builder()
                    .houseNumber(postRequest.getDetails().getHouseNumber())
                    .state(postRequest.getDetails().getState())
                    .street(postRequest.getDetails().getStreet())
                    .build();
            Optional<Address> foundAddress = addressRepository.findAddressByStateAndStreetAndHouseNumber(
                    address.getState(), address.getStreet(), address.getHouseNumber()
            );
            if (foundAddress.isPresent()){
                throw new PostException("House with house number "+address.getHouseNumber()+" already" +
                        "exist in the same locality");
            }

            Apartment apartment = Apartment.builder()
                    .houseType(postRequest.getDetails().getHouseType())
                    .description(postRequest.getDetails().getHouseDescription())
                    .address(address)
                    .id(postRequest.getDetails().getHouseId())
                    .build();

            Post post = Post.builder()
                    .title(postRequest.getDetails().getTitle())
                    .description(postRequest.getDetails().getMainDescription())
                    .dateCreated(LocalDateTime.now())
                    .dateUploaded(LocalDateTime.now())
                    .apartment(apartment)
                    .build();
            Post savedPost = postRepository.save(post);
            Map<String, String> uploadResult = cloudService.upload(details.getMultipartFile());
            savedPost.setPhotoUrl(uploadResult.get("photoUrl"));
            return savedPost;
    }
}
