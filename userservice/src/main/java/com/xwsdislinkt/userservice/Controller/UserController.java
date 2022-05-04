package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.Configuration.Security.TokenUtils;
import com.xwsdislinkt.userservice.DTO.LoginDTO;
import com.xwsdislinkt.userservice.DTO.UserDTO;
import com.xwsdislinkt.userservice.DTO.UserTokenState;
import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.UserService;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    UserService userService;
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
    public ResponseEntity<UserTokenState> login(@RequestBody LoginDTO dto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsernameOrEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = (User) authentication.getPrincipal();
        var jwt = tokenUtils.generateToken(user.getUsername());
        var expiresIn = tokenUtils.getExpiredIn();

        return new ResponseEntity<>(new UserTokenState(jwt, expiresIn), HttpStatus.OK);
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
}
