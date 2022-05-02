package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Repository.UserRepository;
import com.xwsdislinkt.userservice.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceMongoDb implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> get(String id) {
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
    public User findByUsername(String username) { return userRepository.findByUsername(username); }
}
