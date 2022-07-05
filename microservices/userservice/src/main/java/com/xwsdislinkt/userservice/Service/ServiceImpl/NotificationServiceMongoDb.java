package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.Notification;
import com.xwsdislinkt.userservice.Repository.NotificationRepository;
import com.xwsdislinkt.userservice.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServiceMongoDb implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Optional<Notification> get(String id) { return notificationRepository.findById(id); }

    @Override
    public List<Notification> findAll() {  return notificationRepository.findAll(); }

    @Override
    public Notification save(Notification notification) {
        notification.setId(UUID.randomUUID().toString());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(Notification notification) { return notificationRepository.save(notification); }

    @Override
    public void delete(String id) { notificationRepository.deleteById(id); }

    @Override
    public List<Notification> findAllByUserId(String id) { return notificationRepository.findAllByUserId(id); }
}
