package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> get(String  id);
    List<User> findAll();
    User save(User u);
    User update(User u);
    void delete(String  id);
    User findByUsernameOrEmail(String usernameOrEmail);
    Boolean followUser(String  userId, String  toFollowUserId);
    Boolean approveFollow(String userId, String followerId);
    List<User> searchUsers(String criteria);
    User findLoggedInUser();
    String generateUserApiKey();
    User findByApiKey(String apiKey);
    Boolean unfollowUser(String  userId, String  toUnfollowUserId);
    List<User> searchAllUsers(String criteria);
    List<User> getFollowersAndFollowed();
    Boolean blockUser(String userId, String userToBlockId);
}
