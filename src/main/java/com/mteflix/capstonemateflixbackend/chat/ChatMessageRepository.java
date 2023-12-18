package com.mteflix.capstonemateflixbackend.chat;

import com.mteflix.capstonemateflixbackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findBySenderAndRecipient(User sender, User recipient);
}
