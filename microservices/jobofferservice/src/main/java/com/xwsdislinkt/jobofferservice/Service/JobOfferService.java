package com.xwsdislinkt.jobofferservice.Service;

import com.xwsdislinkt.jobofferservice.Model.JobOffer;

public interface JobOfferService {
    JobOffer save(JobOffer jobOffer);
    String findUserByApiKey(String apiKey);
}
