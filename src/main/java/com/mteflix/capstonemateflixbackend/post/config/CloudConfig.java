package com.mteflix.capstonemateflixbackend.post.config;

import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;

@Configuration
@Data
public class CloudConfig {
    @Value("${cloud.api.key}")
    private String apikey;
    @Value("${cloud.api.name}")
    private String cloudName;
    @Value("${cloud.api.secret}")
    private String apiSecret;
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key",apikey,
                "api_secret",apiSecret,
                "secure", "true"));
    }
}
