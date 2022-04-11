package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import com.xwsdislinkt.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceMongoDb implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User u) {
        return userRepository.save(u);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
