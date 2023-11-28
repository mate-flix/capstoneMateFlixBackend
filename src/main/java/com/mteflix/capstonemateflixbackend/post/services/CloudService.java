package com.mteflix.capstonemateflixbackend.post.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudService {
    Map<String, String> upload(MultipartFile file) throws IOException;
}
