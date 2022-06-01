package com.example.agent_api.Service;

import com.example.agent_api.Model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> get(String  id);
    List<Comment> findAll();
    Comment save(Comment c);
    Comment update(Comment c);
    void delete(String  id);
}
