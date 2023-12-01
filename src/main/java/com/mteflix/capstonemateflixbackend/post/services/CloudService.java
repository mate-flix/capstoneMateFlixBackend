package com.mteflix.capstonemateflixbackend.post.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudService {
    String upload(MultipartFile file) throws IOException;
}
