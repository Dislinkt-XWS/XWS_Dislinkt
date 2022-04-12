package com.example.messageservice.Service;

import com.example.messageservice.Model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> get(Long id);
    List<Message> findAll();
    Message save(Message m);
    void delete(Long id);
}
