package com.example.agent_api.Repository;

import com.example.agent_api.Model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    @Query("{ 'status' : 'PENDING' }")
    List<Company> getAllPendingCompanies();
}
