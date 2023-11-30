package com.mteflix.capstonemateflixbackend.chat;

import com.mteflix.capstonemateflixbackend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;


    private Long senderId;
        private Long recipientId;


    private String messageType;


    private String message;


    private String imageUrl;


    private Date timeStamp;


}
