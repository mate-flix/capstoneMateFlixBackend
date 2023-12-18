package com.mteflix.capstonemateflixbackend.chat;
import com.mteflix.capstonemateflixbackend.globalDTO.Response;
import com.mteflix.capstonemateflixbackend.user.User;
import com.mteflix.capstonemateflixbackend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final UserService userService;


    public Response  save(CreateChatRequest createChatRequest) {
        ChatMessage chatMessage = new ChatMessage();
        Optional<User> sender = userService.getUserBy(createChatRequest.getSenderEmail());
        Optional<User> recipient = userService.getUserBy(createChatRequest.getRecipientEmail());
      chatMessage.setSender(sender.get());
      chatMessage.setRecipient(recipient.get());
      chatMessage.setContent(createChatRequest.getContent());
      chatMessage.setTimestamp(LocalDateTime.now());
      repository.save(chatMessage);
        return new Response("chat created");
    }

    public List<ChatMessage> findChatMessages(GetMessagesRequest getMessagesRequest) {
        Optional<User> sender = userService.getUserBy(getMessagesRequest.getSenderEmail());
        Optional<User> recipient = userService.getUserBy(getMessagesRequest.getRecipientEmail());
        return repository.findBySenderAndRecipient(sender.get(), recipient.get());
    }
}
