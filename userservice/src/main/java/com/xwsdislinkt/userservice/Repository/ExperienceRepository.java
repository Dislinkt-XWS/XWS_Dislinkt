package com.xwsdislinkt.userservice.Repository;


import com.xwsdislinkt.userservice.Model.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExperienceRepository extends MongoRepository<Experience, String> {
    List<Experience> findAllByUserId(String id);
}
