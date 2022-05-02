package com.example.messageservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String id;
    private String senderId;
    private String receiverId;
    private String text;
    private LocalDateTime messageSent;
}
