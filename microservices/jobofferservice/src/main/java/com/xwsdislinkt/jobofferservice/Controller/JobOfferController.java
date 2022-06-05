package com.xwsdislinkt.jobofferservice.Controller;

import com.xwsdislinkt.jobofferservice.DTO.JobOfferDTO;
import com.xwsdislinkt.jobofferservice.Model.JobOffer;
import com.xwsdislinkt.jobofferservice.Service.JobOfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value= "api/joboffers")
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/external")
    public ResponseEntity<JobOfferDTO> saveExternal(@RequestBody JobOfferDTO dto, @RequestHeader String authorization){
        String userId = jobOfferService.findUserByApiKey(authorization);
        if(userId == "") {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var jobOffer = modelMapper.map(dto, JobOffer.class);
        jobOffer.setPublisherId(userId);
        jobOffer = jobOfferService.save(jobOffer);
        return new ResponseEntity<>(modelMapper.map(jobOffer, JobOfferDTO.class), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<JobOfferDTO> save(@RequestBody JobOfferDTO dto){
        JobOffer jobOffer = jobOfferService.save(modelMapper.map(dto, JobOffer.class));
        return new ResponseEntity<>(modelMapper.map(jobOffer, JobOfferDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value="/search/{criteria}")
    public ResponseEntity<List<JobOfferDTO>> search(@PathVariable("criteria") String criteria) {
        var jobOffers = jobOfferService.searchOffers(criteria);
        List<JobOfferDTO> jobOfferDTOS = jobOffers
                .stream()
                .map(jobOffer -> modelMapper.map(jobOffer, JobOfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(jobOfferDTOS, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<JobOfferDTO>> findAllJobOffers() {
        var jobOffers = jobOfferService.findAll();
        List<JobOfferDTO> jobOfferDTOS = jobOffers
                .stream()
                .map(jobOffer -> modelMapper.map(jobOffer, JobOfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(jobOfferDTOS, HttpStatus.OK);
    }
}
