package com.example.agent_api.Service;

import com.example.agent_api.Model.Company;
import com.example.agent_api.Model.Enumerations.Status;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Optional<Company> get(String  id);
    List<Company> findAll();
    Company save(Company c);
    Company update(Company c);
    void delete(String  id);
    Company approveCompanyRequest(String id, Status status);
    List<Company> getAllPendingCompanies();
}
