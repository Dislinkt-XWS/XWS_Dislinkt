package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, String> {
}
