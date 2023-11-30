package com.mteflix.capstonemateflixbackend.post.utils;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.Details;
import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.model.Address;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.User;
import com.mteflix.capstonemateflixbackend.post.data.repository.AddressRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.ApartmentRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.UserRepository;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import com.mteflix.capstonemateflixbackend.post.services.CloudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Utils {
    private final CloudService cloudService;
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    public void uploadWithPhoto(PostRequest postRequest) throws IOException {
        Optional<User> userId = userRepository.findById(postRequest.getDetails().getUserId());

        if (userId.isEmpty()){
            throw new PostException("User with id "+postRequest.getDetails().getUserId()+" " +
                    "not found");
        }
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
                .description(postRequest.getDetails().getMainDescription())
                .address(address)
                .dateCreated(LocalDateTime.now())
                .dateUploaded(LocalDateTime.now())
                .build();
        String url = cloudService.upload(details.getMultipartFile());
        Long uploaderId = userId.get().getId();

        apartment.setPhotoUrl(Collections.singleton(url));
        apartment.setUserId(uploaderId);
        apartmentRepository.save(apartment);
    }
}