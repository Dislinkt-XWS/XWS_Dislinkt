package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String > {
    List<Notification> findAllByUserId(String id);
}
