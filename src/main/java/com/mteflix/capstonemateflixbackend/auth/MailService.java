package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.EmailRequest;
import africa.semicolon.gemstube.dtos.response.EmailResponse;

public interface MailService {
    EmailResponse sendMail(EmailRequest emailRequest);
}
