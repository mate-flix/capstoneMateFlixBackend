package com.mteflix.capstonemateflixbackend.notification;

import com.mteflix.capstonemateflixbackend.globalDTO.Response;

public interface NotificationService {
    Response sendNotification (NotificationRequest noficationRequest);
}
