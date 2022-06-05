package com.xwsdislinkt.userservice.Service.GrpcService;

import com.xwsdislinkt.userservice.FollowersRequest;
import com.xwsdislinkt.userservice.FollowersResponse;
import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.UserService;
import com.xwsdislinkt.userservice.UserServiceGrpc;
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
}
