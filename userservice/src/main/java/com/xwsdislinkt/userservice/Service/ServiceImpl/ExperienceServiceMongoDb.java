package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.Experience;
import com.xwsdislinkt.userservice.Repository.ExperienceRepository;
import com.xwsdislinkt.userservice.Service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExperienceServiceMongoDb implements ExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    @Override
    public Optional<Experience> get(String id) { return experienceRepository.findById(id); }

    @Override
    public List<Experience> findAll() { return experienceRepository.findAll(); }

    @Override
    public Experience save(Experience experience)
    {
        experience.setId(UUID.randomUUID().toString());
        return experienceRepository.save(experience);
    }

    @Override
    public Experience update(Experience experience) { return experienceRepository.save(experience); }

    @Override
    public void delete(String id) { experienceRepository.deleteById(id); }
}
