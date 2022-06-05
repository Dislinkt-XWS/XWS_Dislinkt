package com.example.agent_api.Service.Impl;


import com.example.agent_api.Model.JobOffer;
import com.example.agent_api.Repository.JobOfferRepository;
import com.example.agent_api.Service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    @Autowired
    JobOfferRepository jobOfferRepository;


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
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public JobOffer update(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public void delete(String id) {
        jobOfferRepository.deleteById(id);
    }
}
