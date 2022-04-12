package com.example.postservice.Service;

import com.example.postservice.Model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> get(Long id);
    List<Post> findAll();
    Post save(Post p);
    void delete(Long id);
}
