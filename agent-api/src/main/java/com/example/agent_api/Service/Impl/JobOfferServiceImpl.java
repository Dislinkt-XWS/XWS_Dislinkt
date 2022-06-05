package com.example.agent_api.Service.Impl;

import com.example.agent_api.Model.JobOffer;
import com.example.agent_api.Repository.JobOfferRepository;
import com.example.agent_api.Service.CompanyService;
import com.example.agent_api.Service.JobOfferService;
import com.example.agent_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    @Autowired
    JobOfferRepository jobOfferRepository;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @Override
    public Optional<JobOffer> get(String id) {
        return jobOfferRepository.findById(id);
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer save(JobOffer jobOffer) {
        var company = companyService.get(jobOffer.companyId).get();
        var userId = userService.findLoggedInUser().getId();
        if (!Objects.equals(company.ownerId, userId))
            return null;

        jobOffer.setId(UUID.randomUUID().toString());
        jobOffer.setPublisherId(userId);
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public void delete(String id) {
        jobOfferRepository.deleteById(id);
    }
}
