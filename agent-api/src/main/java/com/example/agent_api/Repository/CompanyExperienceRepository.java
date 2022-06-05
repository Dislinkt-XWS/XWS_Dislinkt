package com.example.agent_api.Repository;

import com.example.agent_api.Model.CompanyExperience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyExperienceRepository extends MongoRepository<CompanyExperience, String> {
    @Query("{$and: [ { 'experienceType' : 'COMMENT' }, { 'companyId' : ?0 } ]}")
    List<CompanyExperience> findCommentsByCompany(String companyId);

    @Query("{$and: [ {'experienceType' : 'SALARY'}, { 'companyId' : ?0 } ]}")
    List<CompanyExperience> findSalariesByCompany(String companyId);

    @Query("{$and: [ {'experienceType' : 'INTERVIEW'}, { 'companyId' : ?0 } ]}")
    List<CompanyExperience> findInterviewsByCompany(String companyId);
}
