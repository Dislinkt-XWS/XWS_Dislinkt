package com.xwsdislinkt.jobofferservice.Service.ServiceImpl;

import com.xwsdislinkt.jobofferservice.Model.JobOffer;
import com.xwsdislinkt.jobofferservice.Repository.JobOfferRepository;
import com.xwsdislinkt.jobofferservice.Service.JobOfferService;
import com.xwsdislinkt.userservice.ApiKeyRequest;
import com.xwsdislinkt.userservice.ApiKeyResponse;
import com.xwsdislinkt.userservice.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JobOfferServiceMongoDb implements JobOfferService {
    @Autowired
    JobOfferRepository jobOfferRepository;

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @Override
    public JobOffer save(JobOffer jobOffer) {
        jobOffer.setId(UUID.randomUUID().toString());
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public String findUserByApiKey(String apiKey) {
        ApiKeyRequest request = ApiKeyRequest.newBuilder().setApiKey(apiKey).build();
        ApiKeyResponse response = userServiceStub.findByApiKey(request);
        return response.getUserId();
    }

    @Override
    public List<JobOffer> searchOffers(String criteria) {
        return jobOfferRepository.searchOffers(criteria);
    }

    @Override
    public List<JobOffer> findAll() { return jobOfferRepository.findAll(); }
}
