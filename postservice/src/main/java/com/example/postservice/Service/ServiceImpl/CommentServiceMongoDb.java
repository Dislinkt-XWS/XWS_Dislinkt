package com.example.postservice.Service.ServiceImpl;

import com.example.postservice.Model.Comment;
import com.example.postservice.Repository.CommentRepository;
import com.example.postservice.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceMongoDb implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment findById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        comment.setId(UUID.randomUUID().toString());
        comment.setDatePosted(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }
}
