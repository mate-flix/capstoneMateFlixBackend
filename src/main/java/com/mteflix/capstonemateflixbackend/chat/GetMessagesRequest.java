package com.mteflix.capstonemateflixbackend.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMessagesRequest {
  private  String senderEmail;
  private String recipientEmail;
}