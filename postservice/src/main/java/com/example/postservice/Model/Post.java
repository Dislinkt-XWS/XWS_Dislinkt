package com.example.postservice.Model;

import lombok.*;
import org.bson.types.ObjectId;
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
    private ObjectId id;
    private ObjectId userId;
    private String textContent;
    private String imagePath;
    private List<ObjectId> userLikes;
    private List<ObjectId> userDislikes;
    private LocalDateTime datePosted;

}
