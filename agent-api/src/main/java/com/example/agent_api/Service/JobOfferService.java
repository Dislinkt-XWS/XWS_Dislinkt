package com.example.agent_api.Service;

import com.example.agent_api.Model.JobOffer;

import java.util.List;
import java.util.Optional;

public interface JobOfferService {
    Optional<JobOffer> get(String  id);
    List<JobOffer> findAll();
    JobOffer save(JobOffer jobOffer);
    JobOffer update(JobOffer jobOffer);
    void delete(String  id);
}
