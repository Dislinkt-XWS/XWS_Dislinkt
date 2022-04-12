package com.example.postservice.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private Long id;
    private Long userId;
    private String textContent;
    private String imagePath;
    private List<Long> userLikes;
    private List<Long> userDislikes;
    private LocalDateTime datePosted;

}
