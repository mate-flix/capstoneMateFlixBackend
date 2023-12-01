package com.mteflix.capstonemateflixbackend.post.utils;

import com.mteflix.capstonemateflixbackend.post.data.dto.request.Details;
import com.mteflix.capstonemateflixbackend.post.data.dto.request.PostRequest;
import com.mteflix.capstonemateflixbackend.post.data.model.Address;
import com.mteflix.capstonemateflixbackend.post.data.model.Apartment;
import com.mteflix.capstonemateflixbackend.post.data.model.Profile;
import com.mteflix.capstonemateflixbackend.post.data.model.User;
import com.mteflix.capstonemateflixbackend.post.data.repository.AddressRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.ApartmentRepository;
import com.mteflix.capstonemateflixbackend.post.data.repository.UserRepository;
import com.mteflix.capstonemateflixbackend.post.services.CloudService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilsTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CloudService cloudService;
    @Mock
    private ApartmentRepository apartmentRepository;
    @InjectMocks
    private Utils utils;

    @Test
    public void testUploadWithPhoto() throws IOException {
        PostRequest postRequest = createTestPostRequest();
        User mockUser = createUser();
        createMockAddress();

        when(userRepository.findById(any())).thenReturn(Optional.of(mockUser));
        when(addressRepository.findAddressByStateAndStreetAndHouseNumber(
                postRequest.getDetails().getState(),
                postRequest.getDetails().getStreet(),
                postRequest.getDetails().getHouseNumber()))
                .thenReturn(Optional.empty());
        when(cloudService.upload(postRequest.getDetails().getMultipartFile())).thenReturn("mocked-url");
        ArgumentCaptor<Apartment> captor = ArgumentCaptor.forClass(Apartment.class);

        utils.uploadWithPhoto(postRequest);

        verify(apartmentRepository, times(1)).save(captor.capture());
        Apartment savedApartment = captor.getValue();
        assertNotNull(savedApartment.getPhotoUrl());
        assertEquals(mockUser, savedApartment.getOwner());
    }
    public PostRequest createTestPostRequest() throws IOException {
        Path path = Paths.get("C:\\Users\\GIDEON\\OneDrive\\Desktop\\Capstone Project\\capstoneMateFlixBackend-main\\capstoneMateFlixBackend-main\\src\\" +
                "main\\java\\com\\mteflix\\capstonemateflixbackend\\post\\utils\\libbyrose.jpg");
        byte[] fileContent = Files.readAllBytes(path);

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "libbyrose.jpg",
                "image/jpeg",
                fileContent);

        Details details = new Details();
        details.setHouseType("Bungalow");
        details.setMainDescription("My fine bungalow");
        details.setHouseNumber(1L);
        details.setState("Lagos");
        details.setStreet("Herbert Macaulay");
        details.setUserId(1L);
        details.setMultipartFile(mockMultipartFile);

        PostRequest postRequest = new PostRequest();
        postRequest.setDetails(details);
        return postRequest;
    }
    public User createUser(){
        Profile profile = new Profile();
        profile.setCourse("Maths");
        User user = new User();
        user.setProfile(profile);
        user.setId(1L);
        return user;
    }
    public Address createMockAddress(){
        Address address = new Address();
        address.setHouseNumber(1L);
        address.setState("Lagos");
        address.setStreet("Herbert Macaulay way");
        address.setId(1L);
        return address;
    }
}