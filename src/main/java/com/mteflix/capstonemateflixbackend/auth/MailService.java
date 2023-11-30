package com.mteflix.capstonemateflixbackend.auth;


public interface MailService {
    EmailResponse sendMail(EmailRequest emailRequest);
}
