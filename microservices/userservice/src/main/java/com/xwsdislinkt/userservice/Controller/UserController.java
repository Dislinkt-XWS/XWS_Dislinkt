package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.Configuration.Security.TokenUtils;
import com.xwsdislinkt.userservice.DTO.*;
import com.xwsdislinkt.userservice.Model.*;
import com.xwsdislinkt.userservice.Service.*;
import com.xwsdislinkt.userservice.Service.GrpcService.UserServiceImpl;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ExperienceService experienceService;
    @Autowired
    SkillService skillService;
    @Autowired
    InterestService interestService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenUtils tokenUtils;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated UserDTO dto){
        if(userService.findByUsernameOrEmail(dto.getEmail()) != null) {
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.BAD_REQUEST);
        }

        if(userService.findByUsernameOrEmail(dto.getUsername()) != null) {
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.BAD_REQUEST);
        }

        var user = modelMapper.map(dto, User.class);
        return new ResponseEntity<>(modelMapper.map(
                userService.save(user), UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Validated UserDTO dto) {
        var user = userService.findLoggedInUser();

        if (user == null)
            return new ResponseEntity<>("No user logged in!", HttpStatus.BAD_REQUEST);

        if (dto.getEmail() != null && !dto.getEmail().isEmpty() && !dto.getEmail().equals(user.getEmail())) {
            if (userService.findByUsernameOrEmail(dto.getEmail()) != null)
                return new ResponseEntity<>("This email is already taken!", HttpStatus.BAD_REQUEST);
            else
                user.setEmail(dto.getEmail());
        }
        if (dto.getFullName() != null && !dto.getFullName().isEmpty())
            user.setFullName(dto.getFullName());
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isEmpty())
            user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getUserGender() != null)
            user.setUserGender(dto.getUserGender());
        if (dto.getDateOfBirth() != null)
            user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getBio() != null && !dto.getBio().isEmpty())
            user.setBio(dto.getBio());
        if (dto.getUsername() != null && !dto.getUsername().isEmpty() && !dto.getUsername().equals(user.getUsername())) {
            if (userService.findByUsernameOrEmail(dto.getUsername()) != null)
                return new ResponseEntity<>("This username is already taken!", HttpStatus.BAD_REQUEST);
            else
                user.setUsername(dto.getUsername());
        }
        if (dto.getIsPrivate() != null)
            user.setIsPrivate(dto.getIsPrivate());

        return new ResponseEntity<>(modelMapper.map(
                userService.update(user), UserDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> get(){
        var users = userService.findAll();
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsernameOrEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = (User) authentication.getPrincipal();
        var jwt = tokenUtils.generateToken(user.getUsername());
        var expiresIn = tokenUtils.getExpiredIn();

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping(value = "/follow/approve" )
    public ResponseEntity<String> approveFollow(@RequestBody Map<String, String> userToApproveIds){
        var user = userService.findLoggedInUser();

        if(user == null){
            return new ResponseEntity<>("User must be logged in to approve a follow", HttpStatus.BAD_REQUEST);
        }
        if(userService.approveFollow(user.getId(), userToApproveIds.get("userToApproveIds"))){
            return new ResponseEntity<>("Successfully approved follow ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldnt execture the approve follow operation.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/follow")
    public ResponseEntity<String> follow(@RequestBody Map<String, String> userToFollowId){
        var user = userService.findLoggedInUser();
        System.out.println("Controller logged in user id: " + user.getId());
        System.out.println("Controller user to follow id: " +userToFollowId);
        if(user == null){
            return new ResponseEntity<>("User must be logged in to follow other accounts", HttpStatus.BAD_REQUEST);
        }

        if(userService.followUser(user.getId(), userToFollowId.get("userToFollowId"))){

            Notification notification = new Notification();
            notification.setUserId(userToFollowId.get("userToFollowId"));
            notification.setSenderId(user.getId());
            notification.setTime(LocalDateTime.now());
            notification.setText("New follower: " + user.getId());
            notificationService.save(notification);

            return new ResponseEntity<>("Successfully followed user. ", HttpStatus.OK);
        }


        return new ResponseEntity<>("Couldnt execute the follow operation.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/search/{criteria}")
    public ResponseEntity<List<UserDTO>> search(@PathVariable("criteria") String criteria) {
        var users = userService.searchUsers(criteria);
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/whoami")
    public ResponseEntity<?> loggedIn() {
        var current = userService.findLoggedInUser();

        if (current == null)
            return new ResponseEntity<>("No user is logged in!", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") String id) {
        var user = userService.get(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value="/generateapitoken")
    public ResponseEntity<String> generateApiToken(){
        var user = userService.findLoggedInUser();
        user.setUserApiKey(userService.generateUserApiKey());
        userService.update(user);
        return new ResponseEntity<>(user.getUserApiKey(), HttpStatus.OK);
    }

    @PostMapping(value = "/unfollow")
    public ResponseEntity<String> unfollow(@RequestBody Map<String, String> userToUnfollowId){
        var user = userService.findLoggedInUser();
        System.out.println("Controller logged in user id: " + user.getId());
        System.out.println("Controller user to unfollow id: " + userToUnfollowId);
        if(user == null){
            return new ResponseEntity<>("User must be logged in to unfollow other accounts", HttpStatus.BAD_REQUEST);
        }

        if(userService.unfollowUser(user.getId(), userToUnfollowId.get("userToUnfollowId"))){
            return new ResponseEntity<>("Successfully unfollowed user. ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldnt execute the unfollow operation.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/search-all/{criteria}")
    public ResponseEntity<List<UserDTO>> searchAll(@PathVariable("criteria") String criteria) {
        var users = userService.searchAllUsers(criteria);
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
}
