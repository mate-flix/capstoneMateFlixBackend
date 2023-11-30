package com.mteflix.capstonemateflixbackend.chat;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.user.User;
import com.mteflix.capstonemateflixbackend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@RequestMapping("/api/messages")
@RestController
@AllArgsConstructor
public class MessageController {


    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(@PathVariable Long senderId, @PathVariable Long recipientId) throws MateFlixException {
        // Fetch sender and recipient user objects from the database (you may need to adapt this)
        User sender = userService.getUserById(senderId);
        User recipient = userService.getUserById(recipientId);

        if (sender == null || recipient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Message> messages = messageService.getMessagesBetweenUsers(sender, recipient);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMessages")
    public ResponseEntity<String> deleteMessages(@RequestBody List<Long> messages) {
        try {
            if (messages == null || messages.isEmpty()) {
                return new ResponseEntity<>("Invalid request body!", HttpStatus.BAD_REQUEST);
            }

            messageService.deleteMessages(messages);

            return new ResponseEntity<>("Messages deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping ("/messages")
    public ResponseEntity<String> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String messageType,
            @RequestParam String messageText,
            @RequestParam(required = false) MultipartFile imageFile
    ) {
        try {
            // Fetch sender and recipient user objects from the database (you may need to adapt this)
            User sender = userService.getUserById(senderId);
            User recipient = userService.getUserById(recipientId);

            if (sender == null || recipient == null) {
                return new ResponseEntity<>("Sender or recipient not found", HttpStatus.NOT_FOUND);
            }

            Message newMessage = new Message();
            newMessage.setSender(sender);
            newMessage.setRecipient(recipient);
            newMessage.setMessageType(messageType);
            newMessage.setMessage(messageText);
            newMessage.setTimeStamp(new Date());
            newMessage.setImageUrl(messageType.equals("image") ? "/path/to/default/image" : null); // Set default image path if no image

            if (imageFile != null && !imageFile.isEmpty()) {
                // Process and save the image file, update the imageUrl accordingly
                // Example: newMessage.setImageUrl("/path/to/uploaded/image");
            }

            messageService.saveMessage(newMessage);

            return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
        } catch (Exception | MateFlixException e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}