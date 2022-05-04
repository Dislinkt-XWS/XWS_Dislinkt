package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> get(String  id);
    List<User> findAll();
    User save(User u);
    User update(User u);
    void delete(String  id);
    User findByUsername(String username);
    Boolean followUser(String  userId, String  toFollowUserId);
    Boolean approveFollow(String userId, String followerId);
}
