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
        //TODO: Implement getting followers for logged user so that it is grpc compatible
        /*var loggedInUser = userService.findLoggedInUser();
        List<String> allUsers = loggedInUser.getFollowedUsers();
        allUsers.add(loggedInUser.getId());*/
        List<String> allUsers = new ArrayList<>();
        for(User user : userService.findAll()) {
            allUsers.add(user.getId());
        }

        FollowersResponse followersResponse = FollowersResponse.newBuilder()
                .addAllFollowers(allUsers)
                .build();

        responseObserver.onNext(followersResponse);
        responseObserver.onCompleted();
    }
}
