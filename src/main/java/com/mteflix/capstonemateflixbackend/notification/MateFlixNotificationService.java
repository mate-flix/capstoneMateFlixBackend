package com.mteflix.capstonemateflixbackend.notification;

import com.mteflix.capstonemateflixbackend.globalDTO.Response;
import com.mteflix.capstonemateflixbackend.mail.EmailRequest;
import com.mteflix.capstonemateflixbackend.mail.MateEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MateFlixNotificationService implements NotificationService{
    private final MateEmailService mateEmailService;
    private final NotificationRepository notificationRepository;

    @Override
    public Response sendNotification(NotificationRequest noficationRequest) {
        Notification notification = new Notification();
        notification.setContent(noficationRequest.getContent());
        notification.setTimestamp(LocalDateTime.now());
        notification.setContent(noficationRequest.getContent());
        notificationRepository.save(notification);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(noficationRequest.getRecipientEmail());
        emailRequest.setBody(noficationRequest.getContent());
        return new Response("Notification sent successfully");
    }
}
