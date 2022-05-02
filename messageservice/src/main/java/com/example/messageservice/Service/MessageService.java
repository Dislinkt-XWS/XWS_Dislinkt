package com.example.messageservice.Service;

import com.example.messageservice.Model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> get(String id);
    List<Message> findAll();
    Message save(Message m);
    void delete(String id);
}
