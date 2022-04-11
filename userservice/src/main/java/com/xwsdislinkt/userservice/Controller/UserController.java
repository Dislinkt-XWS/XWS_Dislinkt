package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Validated User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<User>> get(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
