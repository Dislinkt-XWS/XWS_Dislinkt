package com.xwsdislinkt.userservice.Service.GrpcService;

import com.xwsdislinkt.userservice.*;
import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserService userService;

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
