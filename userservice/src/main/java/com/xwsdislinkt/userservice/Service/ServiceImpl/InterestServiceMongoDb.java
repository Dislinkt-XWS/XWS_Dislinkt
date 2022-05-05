package com.xwsdislinkt.userservice.Service.ServiceImpl;

import com.xwsdislinkt.userservice.Model.Interest;
import com.xwsdislinkt.userservice.Repository.InterestRepository;
import com.xwsdislinkt.userservice.Service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InterestServiceMongoDb implements InterestService {

    @Autowired
    InterestRepository interestRepository;

    @Override
    public Optional<Interest> get(String id) { return interestRepository.findById(id); }

    @Override
    public List<Interest> findAll() { return interestRepository.findAll(); }

    @Override
    public Interest save(Interest interest) {
        interest.setId(UUID.randomUUID().toString());
        return interestRepository.save(interest); }

    @Override
    public Interest update(Interest interest) { return interestRepository.save(interest); }

    @Override
    public void delete(String id) { interestRepository.deleteById(id);}
}
