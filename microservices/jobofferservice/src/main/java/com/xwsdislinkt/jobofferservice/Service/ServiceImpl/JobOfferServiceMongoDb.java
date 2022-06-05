package com.xwsdislinkt.jobofferservice.Service.ServiceImpl;

import com.xwsdislinkt.jobofferservice.Repository.JobOfferRepository;
import com.xwsdislinkt.jobofferservice.Service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServiceMongoDb implements JobOfferService {
    @Autowired
    JobOfferRepository jobOfferRepository;
}
