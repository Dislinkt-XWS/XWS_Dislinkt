package com.example.postservice.Service.ServiceImpl;

import com.example.postservice.Model.Post;
import com.example.postservice.Repository.PostRepository;
import com.example.postservice.Service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceMongoDb implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Optional<Post> get(String id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post p) {
        p.setId(UUID.randomUUID().toString());
        return postRepository.save(p);
    }

    @Override
    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
