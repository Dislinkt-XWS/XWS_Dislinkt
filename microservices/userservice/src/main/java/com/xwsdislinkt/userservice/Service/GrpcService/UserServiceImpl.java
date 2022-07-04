package com.xwsdislinkt.userservice.Service.GrpcService;

import com.xwsdislinkt.userservice.*;
import com.xwsdislinkt.userservice.Model.Notification;
import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.NotificationService;
import com.xwsdislinkt.userservice.Service.UserService;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Override
    public void followers(FollowersRequest request, StreamObserver<FollowersResponse> responseObserver) {
        String userId = request.getUserId();
        User user = userService.get(userId).get();
        List<String> followers = user.getFollowedUsers();
        followers.add(userId);

        FollowersResponse followersResponse = FollowersResponse.newBuilder()
                .addAllFollowers(followers)
                .build();

        responseObserver.onNext(followersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void addNotification(NotificationRequest request, StreamObserver<NotificationResponse> responseObserver) {
        String senderId = request.getSenderId();
        System.out.println("User ID:" + senderId);
        User user = userService.get(senderId).get();


        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setSenderId(request.getSenderId());
        notification.setText(request.getText() + " from " + user.getUsername());
        notification.setTime(LocalDateTime.now());
        notification = notificationService.save(notification);

        NotificationResponse notificationResponse = NotificationResponse.newBuilder()
                .setNotificationId(notification.getId())
                .build();

        responseObserver.onNext(notificationResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void findByApiKey(ApiKeyRequest request, StreamObserver<ApiKeyResponse> responseObserver) {
        String apiKey = request.getApiKey();
        User user = userService.findByApiKey(apiKey);
        String userId = (user == null)?"":user.getId();
        ApiKeyResponse apiKeyResponse = ApiKeyResponse.newBuilder()
                .setUserId(userId)
                .build();

        responseObserver.onNext(apiKeyResponse);
        responseObserver.onCompleted();
    }
}
