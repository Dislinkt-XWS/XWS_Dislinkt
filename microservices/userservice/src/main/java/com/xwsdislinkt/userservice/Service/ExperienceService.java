package com.xwsdislinkt.userservice.Service;

import com.xwsdislinkt.userservice.Model.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    Optional<Experience> get(String  id);
    List<Experience> findAll();
    Experience save(Experience experience);
    Experience update(Experience experience);
    void delete(String  id);
    List<Experience> findByUser(String userId);
}
