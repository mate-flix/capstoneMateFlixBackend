package com.mteflix.capstonemateflixbackend.post.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor

public class CloudinaryCloudService implements CloudService{
    private final Cloudinary cloudinary;
    @Override
    public Map<String, String> upload(MultipartFile file) throws IOException {
        Map<?,?> uploader = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String photoUrl = uploader.get("url").toString();
        String publicId = uploader.get("public_id").toString();
        Map<String, String> response = new HashMap<>();
        response.put("photoUrl", photoUrl);
        return response;
    }
}
