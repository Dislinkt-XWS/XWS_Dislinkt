package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.Interest;

import java.util.List;
import java.util.Optional;

public interface InterestService {
    Optional<Interest> get(String  id);
    List<Interest> findAll();
    Interest save(Interest interest);
    Interest update(Interest interest);
    void delete(String  id);
    List<Interest> findAllByUserId(String id);
}
