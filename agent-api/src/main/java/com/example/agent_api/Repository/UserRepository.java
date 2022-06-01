package com.example.agent_api.Repository;

import com.example.agent_api.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ $or: [ { 'username' : ?0 }, { 'email' : ?0 } ]}")
    User findByUsernameOrEmail(String username);

    User getByUsername(String username);
}
