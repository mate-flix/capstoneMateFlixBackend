package com.mteflix.capstonemateflixbackend.chat;

import com.mteflix.capstonemateflixbackend.globalDTO.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/chat")
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload CreateChatRequest createChatRequest) {
        Response savedMsg = chatMessageService.save(createChatRequest);
        messagingTemplate.convertAndSendToUser(
                createChatRequest.getRecipientEmail(), "/queue/messages",
                new ChatNotification(
                        createChatRequest.getSenderEmail(),
                        createChatRequest.getSenderEmail(),
                        createChatRequest.getContent()

        ));
    };

    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@RequestBody GetMessagesRequest getMessagesRequest) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(getMessagesRequest));
    }
}
