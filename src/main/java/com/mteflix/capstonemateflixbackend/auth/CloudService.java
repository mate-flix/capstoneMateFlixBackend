package com.mteflix.capstonemateflixbackend.auth;


import com.mteflix.capstonemateflixbackend.exceptions.MediaUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile multipartFile) throws MediaUploadException, MediaUploadException;
}
