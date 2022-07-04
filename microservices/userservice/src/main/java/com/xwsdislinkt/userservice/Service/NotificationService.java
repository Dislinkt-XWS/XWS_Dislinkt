package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Optional<Notification> get(String id);
    List<Notification> findAll();
    Notification save(Notification notification);
    Notification update(Notification notification);
    void delete(String id);
    List<Notification> findAllByUserId(String id);
}
