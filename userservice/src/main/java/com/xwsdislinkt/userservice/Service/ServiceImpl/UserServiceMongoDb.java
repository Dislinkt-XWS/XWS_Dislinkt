package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import com.xwsdislinkt.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceMongoDb implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override

    public Optional<User> get(String  id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User u)
    {
        u.setId(UUID.randomUUID().toString());
        return userRepository.save(u);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsernameOrEmail(String usernameOrEmail) { return userRepository.findByUsernameOrEmail(usernameOrEmail); }

    @Override
    public Boolean approveFollow(String userId, String followerId){
        User user = userRepository.findById(userId).get();
        User followerUser = userRepository.findById(followerId).get();
        System.out.println(user.getId());
        System.out.println(followerUser.getId());

        if(user.getFollowRequests() != null && user.getFollowRequests().contains(followerId)){
            System.out.println("Puca pre nego sto obrise follow req");

            user.getFollowRequests().remove(followerId);
            System.out.println("Puca pre nego sto obrise follow req poslat");

            followerUser.getPendingFollowRequests().remove(userId);
            System.out.println("Puca pre nego sto doda followera");

            followerUser.getFollowedUsers().add(userId);
            user.getFollowers().add(followerId);
            if(userRepository.save(user) != null && userRepository.save(followerUser) != null){
                return true;
            }
            return false;
        }
        return false;


    }

    @Override
    public List<User> searchUsers(String criteria) {
        return userRepository.searchUsers(criteria);
    }

    @Override
    public Boolean followUser(String userId, String toFollowUserId){
        User user = userRepository.findById(userId).get();
        User userToFollow = userRepository.findById(toFollowUserId).get();
        if(isAlreadyAFollower(user, userToFollow))
            return false;
        if(hasBeenBlocked(user, userToFollow))
            return false;
        if(userToFollow.getIsPrivate()){
            var followed = handlePrivateFollow(user, userToFollow);
            if(followed)
                return true;
        }
        if(user.getFollowedUsers() == null)
            user.setFollowedUsers(new ArrayList<>());
        if(userToFollow.getFollowedUsers() == null)
            userToFollow.setFollowers(new ArrayList<>());

        user.getFollowedUsers().add(toFollowUserId);
        userToFollow.getFollowers().add(userId);
        if(userRepository.save(user) != null && userRepository.save(userToFollow) != null)
            return true;
        return false;
    }
    private Boolean handlePrivateFollow(User user, User userToFollow){
        if(hasSentAFollowRequest(user, userToFollow))
            return false;
        if(user.getPendingFollowRequests() == null)
            user.setPendingFollowRequests(new ArrayList<>());
        if(userToFollow.getFollowRequests() == null)
            userToFollow.setFollowRequests(new ArrayList<>());
        if(user.getFollowedUsers() == null)
            user.setFollowedUsers(new ArrayList<>());
        if(userToFollow.getFollowers() == null)
            userToFollow.setFollowers(new ArrayList<>());
        user.getPendingFollowRequests().add(userToFollow.getId());
        userToFollow.getFollowRequests().add(user.getId());
        if(userRepository.save(user) != null && userRepository.save(userToFollow) != null){
            return true;
        }
        return false;
    }
    private Boolean isAlreadyAFollower(User user, User userToFollow){
        if(user.getFollowers() != null && user.getFollowers().contains(userToFollow.getId()))
            return true;
        return false;
    }
    private Boolean hasBeenBlocked(User user, User userToFollow){
        if(userToFollow.getBlockedUsers() != null && userToFollow.getBlockedUsers().contains(user.getId()))
            return true;
       return false;
    }
    private Boolean hasSentAFollowRequest(User user, User userToFollow){
        if(userToFollow.getFollowRequests() != null && userToFollow.getFollowRequests().contains(user.getId()) )
            return true;
        return false;
    }
}
