package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UserService {


    Optional<User> get(Long id);
    List<User> findAll();
    User save(User u);
    void delete(Long id);
}
