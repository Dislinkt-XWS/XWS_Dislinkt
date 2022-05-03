package com.example.postservice.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String userId;
    private String textContent;
    private String imagePath;
    private List<String> userLikes = new ArrayList<>();
    private List<String> userDislikes = new ArrayList<>();
    private LocalDateTime datePosted;
    private List<String> commentIds = new ArrayList<>();

    public void addLike(String userId) {
        userLikes.add(userId);
    }

    public void removeLike(String userId) {
        userLikes.remove(userId);
    }

    public void addDislike(String userId) {
        userDislikes.add(userId);
    }

    public void removeDislike(String userId) {
        userDislikes.remove(userId);
    }

    public boolean userLiked(String userId) {
        return userLikes.contains(userId);
    }

    public boolean userDisliked(String userId) {
        return userDislikes.contains(userId);
    }
}
