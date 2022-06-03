package com.example.agent_api.Service.Impl;

import com.example.agent_api.Model.Company;
import com.example.agent_api.Model.Enumerations.Role;
import com.example.agent_api.Model.Enumerations.Status;
import com.example.agent_api.Repository.CompanyRepository;
import com.example.agent_api.Service.CompanyService;
import com.example.agent_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserService userService;

    @Override
    public Optional<Company> get(String id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company save(Company c) {
        c.setId(UUID.randomUUID().toString());
        c.setOwnerId(userService.findLoggedInUser().getId());
        c.setStatus(Status.PENDING);
        c.setJobOffers(new ArrayList<>());
        c.setComments(new ArrayList<>());
        return companyRepository.save(c);
    }

    @Override
    public Company update(Company c) {
        return companyRepository.save(c);
    }

    @Override
    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company approveCompanyRequest(String id, Status status) {
        var company = companyRepository.findById(id).get();
        company.setStatus(status);
        companyRepository.save(company);
        var user = userService.get(company.ownerId).get();
        user.setRole(Role.OWNER);
        userService.update(user);
        return company;
    }
}
