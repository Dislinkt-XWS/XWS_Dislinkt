package com.example.agent_api.Repository;

import com.example.agent_api.Model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String> {
}
