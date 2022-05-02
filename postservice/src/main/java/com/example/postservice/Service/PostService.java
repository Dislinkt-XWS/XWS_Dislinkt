package com.example.postservice.Service;

import com.example.postservice.Model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> get(String id);
    List<Post> findAll();
    Post save(Post p);
    void delete(String id);
}
