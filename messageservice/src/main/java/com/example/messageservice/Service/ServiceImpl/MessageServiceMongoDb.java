package com.example.messageservice.Service.ServiceImpl;

import com.example.messageservice.Model.Message;
import com.example.messageservice.Repository.MessageRepository;
import com.example.messageservice.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MessageServiceMongoDb implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Optional<Message> get(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message save(Message m) {
        return messageRepository.save(m);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
