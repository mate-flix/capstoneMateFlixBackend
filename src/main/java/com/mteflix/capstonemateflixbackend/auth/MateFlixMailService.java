package com.mteflix.capstonemateflixbackend.auth;


import com.mteflix.capstonemateflixbackend.config.MailConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@Primary
@AllArgsConstructor
public class MateFlixMailService implements MailService{
    private final MailConfig mailConfig;

    @Override
    public EmailResponse sendMail(EmailRequest emailRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        httpHeaders.set("accept", APPLICATION_JSON_VALUE);
        httpHeaders.set("api-key", mailConfig.getMailApiKey());
        HttpEntity<EmailRequest> requestEntity = new RequestEntity<>(emailRequest,httpHeaders,
                POST, URI.create(""));
        ResponseEntity<EmailResponse> response =
                restTemplate.postForEntity(mailConfig.getBrevoMailUrl(), requestEntity,
                        EmailResponse.class);
        var emailResponse = response.getBody();
        emailResponse.setCode(response.getStatusCode().value());
        return emailResponse;
    }
}
