package com.example.agent_api.Service;

import com.example.agent_api.Model.CompanyExperience;

import java.util.List;
import java.util.Optional;

public interface CompanyExperienceService {
    Optional<CompanyExperience> get(String  id);
    List<CompanyExperience> findAll();
    CompanyExperience save(CompanyExperience c);
    List<CompanyExperience> findCommentsByCompany(String companyId);
    List<CompanyExperience> findSalariesByCompany(String companyId);
    List<CompanyExperience> findInterviewsByCompany(String companyId);
}
