package com.example.postservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private Long userId;
    private String textContent;
    private String imagePath;
    private List<Long> userLikes;
    private List<Long> userDislikes;
    private LocalDateTime datePosted;
}
