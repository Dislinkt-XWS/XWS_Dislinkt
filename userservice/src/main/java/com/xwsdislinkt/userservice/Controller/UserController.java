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
    public ResponseEntity<Boolean> approveFollow(@RequestBody Map<String, String> userIds){
        if(userService.approveFollow(userIds.get("userId"), userIds.get("followerId"))){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/follow")
    public ResponseEntity<Boolean> follow(@RequestBody Map<String, String> userIds){
        System.out.println(userIds.get("userId"));
        System.out.println(userIds.get("toFollowUser"));

        if(userService.followUser(userIds.get("userId"), userIds.get("toFollowUser"))){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
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
