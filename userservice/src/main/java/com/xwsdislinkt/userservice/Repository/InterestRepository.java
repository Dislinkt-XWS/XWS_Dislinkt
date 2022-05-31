package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InterestRepository extends MongoRepository<Interest, String> {
    List<Interest> findAllByUserId(String id);
}
