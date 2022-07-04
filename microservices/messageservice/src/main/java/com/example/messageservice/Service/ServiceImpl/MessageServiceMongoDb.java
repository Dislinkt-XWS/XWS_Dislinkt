package com.example.messageservice.Service.ServiceImpl;

import com.example.messageservice.Model.Message;
import com.example.messageservice.Repository.MessageRepository;
import com.example.messageservice.Service.MessageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceMongoDb implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Optional<Message> get(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message save(Message m) {
        m.setId(UUID.randomUUID().toString());
        m.setMessageSent(LocalDateTime.now());
        return messageRepository.save(m);
    }

    @Override
    public void delete(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> getChat(String user1, String user2) {
        return messageRepository.getChat(user1, user2);
    }
}
