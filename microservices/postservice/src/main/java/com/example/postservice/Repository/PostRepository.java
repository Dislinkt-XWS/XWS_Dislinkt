package com.example.postservice.Repository;

import com.example.postservice.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    public List<Post> findByUserId(String userId);
}
