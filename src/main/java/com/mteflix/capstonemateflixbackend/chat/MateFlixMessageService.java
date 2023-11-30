package com.mteflix.capstonemateflixbackend.chat;


import com.mteflix.capstonemateflixbackend.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MateFlixMessageService implements MessageService{
    private final MessageRepository messageRepository;
    public List<Message> getMessagesBetweenUsers(User sender, User recipient) {
        return messageRepository.findBySenderAndRecipientOrSenderAndRecipient(
                sender, recipient, recipient, sender);
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
    public void deleteMessages(List<Long> messageIds) {
        messageRepository.deleteAllById(messageIds);
    }
}
