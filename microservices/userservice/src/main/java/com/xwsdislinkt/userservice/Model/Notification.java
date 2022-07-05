package com.xwsdislinkt.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String userId;
    private String senderId;
    private String text;
    private LocalDateTime time;
}
