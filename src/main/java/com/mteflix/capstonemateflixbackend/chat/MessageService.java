package com.mteflix.capstonemateflixbackend.chat;

import com.mteflix.capstonemateflixbackend.user.User;

import java.util.List;

public interface MessageService {
    List<Message> getMessagesBetweenUsers(User sender, User recipient);
    void saveMessage(Message message);
   void deleteMessages(List<Long> messageIds);
}
