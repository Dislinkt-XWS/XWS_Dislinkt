package com.example.postservice.Service;

import com.example.postservice.Model.Post;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> get(String id);
    List<Post> findAll();
    Post save(Post p, String authorization) throws Exception;
    Post saveToDatabase(Post p);
    void delete(String id);
    List<Post> findByUserId(String userId);
    Post update(Post p);
    String findCurrentUser(String authorization);
    String uploadImages(MultipartFile images);
    String getBase64(Post post) throws IOException;
}
