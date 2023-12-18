package com.mteflix.capstonemateflixbackend.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String recipientEmail;
    private String content;
}
