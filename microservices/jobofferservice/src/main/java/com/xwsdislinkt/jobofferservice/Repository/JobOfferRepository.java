package com.xwsdislinkt.jobofferservice.Repository;

import com.xwsdislinkt.jobofferservice.Model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String > {
}
