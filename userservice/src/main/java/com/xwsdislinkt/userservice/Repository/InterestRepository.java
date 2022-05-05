package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestRepository extends MongoRepository<Interest, String> {
}
