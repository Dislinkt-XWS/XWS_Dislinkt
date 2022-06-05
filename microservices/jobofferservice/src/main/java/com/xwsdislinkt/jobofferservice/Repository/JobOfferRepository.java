package com.xwsdislinkt.jobofferservice.Repository;

import com.xwsdislinkt.jobofferservice.Model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String > {
    @Query("{ $or: [ { 'position' :  { '$regex' : ?0, '$options' : 'i' } }, { 'jobDescription': { '$regex' : ?0, '$options' : 'i' } } ] }")
    List<JobOffer> searchOffers(String criteria);
}
