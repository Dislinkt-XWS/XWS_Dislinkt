package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.Service.NotificationService;
import com.xwsdislinkt.userservice.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getNotificationsForUser(@PathVariable("id") String id) {
        var user = userService.get(id).get();

        if (user == null)
            return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);

        var notifications = notificationService.findAllByUserId(id);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
