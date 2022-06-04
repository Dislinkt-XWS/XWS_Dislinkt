package com.example.agent_api.Repository;

import com.example.agent_api.Model.CompanyExperience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyExperienceRepository extends MongoRepository<CompanyExperience, String> {
    @Query("{'experienceType' : 'COMMENT'}")
    List<CompanyExperience> findCommentsByCompany(String companyId);

    @Query("{'experienceType' : 'SALARY'}")
    List<CompanyExperience> findSalariesByCompany(String companyId);

    @Query("{'experienceType' : 'INTERVIEW'}")
    List<CompanyExperience> findInterviewsByCompany(String companyId);
}
