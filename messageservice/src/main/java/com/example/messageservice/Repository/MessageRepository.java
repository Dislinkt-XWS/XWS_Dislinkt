package com.example.messageservice.Repository;

import com.example.messageservice.Model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, Long> {
}
