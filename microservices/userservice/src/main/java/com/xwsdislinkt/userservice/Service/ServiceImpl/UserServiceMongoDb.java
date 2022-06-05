package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import com.xwsdislinkt.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceMongoDb implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override

    public Optional<User> get(String  id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User u)
    {
        u.setId(UUID.randomUUID().toString());
        var password = u.getPassword();
        u.setPassword(passwordEncoder.encode(password));
        u.setFollowedUsers(new ArrayList<>());
        u.setBlockedUsers(new ArrayList<>());
        u.setFollowers(new ArrayList<>());
        u.setFollowRequests(new ArrayList<>());
        u.setPendingFollowRequests(new ArrayList<>());
        return userRepository.save(u);
    }

    @Override
    public User update(User u)
    {

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
    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail); }

    @Override
    public User findLoggedInUser(){
        var loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("This is the logged in user " + loggedInUser);
        return userRepository.findByUsernameOrEmail(loggedInUser);
    }

    @Override
    public Boolean approveFollow(String userId, String followerId){
        User user = userRepository.findById(userId).get();
        User followerUser = userRepository.findById(followerId).get();
        System.out.println(user.getId());
        System.out.println(followerUser.getId());

        if(user.getFollowRequests() != null && user.getFollowRequests().contains(followerId)){


            user.getFollowRequests().remove(followerId);

            followerUser.getPendingFollowRequests().remove(userId);

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
        System.out.println("Service logged in user id: " + userId);
        System.out.println("Service user to follow id: " + toFollowUserId);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }
    @Override
    public String generateUserApiKey(){
        SecureRandom random  =new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    @Override
    public User findByApiKey(String apiKey) {
        return userRepository.findByUserApiKey(apiKey);
    }
}
