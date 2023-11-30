package com.mteflix.capstonemateflixbackend.post.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mteflix.capstonemateflixbackend.post.config.CloudConfig;
import com.mteflix.capstonemateflixbackend.post.exception.PostException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@RequiredArgsConstructor

public class CloudinaryCloudService implements CloudService{
    private final CloudConfig config;

    @Override
    public String upload(MultipartFile file) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name",config.getCloudName(),
                "api_key",config.getApikey(),
                "api_secret",config.getApiSecret(),
                "secure", "true"));
        try{
            Map<?, ?> uploadResponse = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return (String) uploadResponse.get("secure-url");
        } catch (IOException ex){
            throw new PostException(ex.getMessage());
        }
    }
}
