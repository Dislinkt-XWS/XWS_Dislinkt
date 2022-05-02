package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends MongoRepository<User, String > {
    public User findByUsername(String username);
}
