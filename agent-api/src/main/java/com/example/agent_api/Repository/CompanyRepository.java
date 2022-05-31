package com.example.agent_api.Repository;

import com.example.agent_api.Model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
}
