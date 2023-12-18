package com.mteflix.capstonemateflixbackend.chat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {
    private String senderEmail;
    private String recipientEmail;
    private String content;
}
