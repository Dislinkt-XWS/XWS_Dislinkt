package com.xwsdislinkt.jobofferservice.Service;

import com.xwsdislinkt.jobofferservice.Model.JobOffer;

import java.util.List;

public interface JobOfferService {
    JobOffer save(JobOffer jobOffer);
    String findUserByApiKey(String apiKey);
    List<JobOffer> searchOffers(String criteria);
}
