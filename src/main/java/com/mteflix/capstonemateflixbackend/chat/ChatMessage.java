package com.mteflix.capstonemateflixbackend.chat;
import com.mteflix.capstonemateflixbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User sender;
    @OneToOne
    private User recipient;

    private String content;
    private LocalDateTime timestamp;

}
