package com.mteflix.capstonemateflixbackend.chat;

import com.mteflix.capstonemateflixbackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndRecipientOrSenderAndRecipient(User sender, User recipient, User recipient2, User sender2);
void deleteAllById(Long id);
}

