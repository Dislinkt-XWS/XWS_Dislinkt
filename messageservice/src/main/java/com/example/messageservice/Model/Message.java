package com.example.messageservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private String text;
    private LocalDateTime messageSent;
}
