package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Optional<Skill> get(String id);
    List<Skill> findAll();
    Skill save(Skill skill);
    Skill update(Skill skill);
    void delete(String  id);
    List<Skill> findAllByUserId(String id);
}
