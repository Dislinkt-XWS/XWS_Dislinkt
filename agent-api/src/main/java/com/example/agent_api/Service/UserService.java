package com.example.agent_api.Service;

import com.example.agent_api.Model.User;
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
}
