package com.example.agent_api.Service.Impl;

import com.example.agent_api.Model.CompanyExperience;
import com.example.agent_api.Model.Enumerations.ExperienceType;
import com.example.agent_api.Repository.CompanyExperienceRepository;
import com.example.agent_api.Service.CompanyExperienceService;
import com.example.agent_api.Service.CompanyService;
import com.example.agent_api.Service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyExperienceServiceImpl implements CompanyExperienceService {
    @Autowired
    CompanyExperienceRepository experienceRepository;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @Override
    public Optional<CompanyExperience> get(String id) {
        return experienceRepository.findById(id);
    }

    @Override
    public List<CompanyExperience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public CompanyExperience save(CompanyExperience comment) {
        var company = companyService.get(comment.getCompanyId()).get();

        if (comment.getExperienceType() == ExperienceType.COMMENT)
            company.addComment(comment.getId());
        else if (comment.getExperienceType() == ExperienceType.INTERVIEW)
            company.addInterview(comment.getId());
        else
            company.addSalary(comment.getId());

        comment.setId(UUID.randomUUID().toString());
        comment.setUserId(userService.findLoggedInUser().getId());
        comment.setDatePosted(new Date());
        return experienceRepository.save(comment);
    }

    @Override
    public List<CompanyExperience> findCommentsByCompany(String companyId) {
        return experienceRepository.findCommentsByCompany(companyId);
    }

    @Override
    public List<CompanyExperience> findSalariesByCompany(String companyId) {
        return experienceRepository.findSalariesByCompany(companyId);
    }

    @Override
    public List<CompanyExperience> findInterviewsByCompany(String companyId) {
        return experienceRepository.findInterviewsByCompany(companyId);
    }
}
