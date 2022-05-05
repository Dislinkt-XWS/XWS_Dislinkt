package com.xwsdislinkt.userservice.Repository;


import com.xwsdislinkt.userservice.Model.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExperienceRepository extends MongoRepository<Experience, String> {
}
