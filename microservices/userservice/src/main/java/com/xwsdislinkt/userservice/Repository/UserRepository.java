package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends MongoRepository<User, String > {
    @Query("{ $or: [ { 'username' : ?0 }, { 'email' : ?0 } ]}")
    User findByUsernameOrEmail(String username);

    @Query("{ $or: [ { 'username' : { '$regex' : ?0, '$options' : 'i' }, 'isPrivate' : false }, { 'fullName' : { '$regex' : ?0, '$options' : 'i' }, 'isPrivate' : false } ]}")
    List<User> searchUsers(String criteria);

    @Query("{username:'?0'}")
    User getUserByUsername(String username);

    User findByUserApiKey(String userApiKey);
}
