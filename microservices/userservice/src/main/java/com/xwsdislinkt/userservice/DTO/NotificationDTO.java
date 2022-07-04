package com.xwsdislinkt.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private String id;
    private String userId;
    private String senderId;
    private String text;
    private LocalDateTime time;
}
