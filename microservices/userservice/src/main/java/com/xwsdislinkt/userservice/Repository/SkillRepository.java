package com.xwsdislinkt.userservice.Repository;

import com.xwsdislinkt.userservice.Model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SkillRepository extends MongoRepository<Skill, String> {
    List<Skill> findAllByUserId(String id);
}
