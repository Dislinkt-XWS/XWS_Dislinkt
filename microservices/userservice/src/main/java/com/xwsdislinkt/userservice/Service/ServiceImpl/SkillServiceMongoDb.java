package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.Skill;
import com.xwsdislinkt.userservice.Repository.SkillRepository;
import com.xwsdislinkt.userservice.Service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillServiceMongoDb implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public Optional<Skill> get(String id) { return skillRepository.findById(id); }

    @Override
    public List<Skill> findAll() { return skillRepository.findAll(); }

    @Override
    public Skill save(Skill skill) {
        skill.setId(UUID.randomUUID().toString());
        return skillRepository.save(skill); }

    @Override
    public Skill update(Skill skill) { return skillRepository.save(skill); }

    @Override
    public void delete(String id) { skillRepository.deleteById(id);}

    @Override
    public List<Skill> findAllByUserId(String id) {
        return skillRepository.findAllByUserId(id);
    }
}
