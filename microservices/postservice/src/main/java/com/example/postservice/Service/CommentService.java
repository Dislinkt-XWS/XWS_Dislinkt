package com.example.postservice.Service;

import com.example.postservice.Model.Comment;

import java.util.List;

public interface CommentService {
    Comment findById(String id);
    List<Comment> findAll();
    Comment save(Comment comment);
    void deleteById(String id);
    List<Comment> findByPostId(String postId);
}
