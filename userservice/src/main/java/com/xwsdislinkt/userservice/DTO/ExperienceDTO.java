package com.xwsdislinkt.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO {
    private String id;
    private String userId;
    private String establishmentName;
    private LocalDateTime start;
    private LocalDateTime end;
    private String role;
}
