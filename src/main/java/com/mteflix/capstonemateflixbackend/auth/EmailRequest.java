package com.mteflix.capstonemateflixbackend.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class EmailRequest {
    private final Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;

    private String subject;
    private String htmlContent;

    public EmailRequest(){
        this.sender=new Sender("gemstube@hotmail.africa","gemstube inc.");
    }
}
