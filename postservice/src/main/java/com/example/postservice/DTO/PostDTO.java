package com.example.postservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String id;
    private String userId;
    private String textContent;
    private String imagePath;
    private List<ObjectId> userLikes;
    private List<ObjectId> userDislikes;
    private LocalDateTime datePosted;
}
