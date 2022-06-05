package com.xwsdislinkt.jobofferservice.Controller;

import com.xwsdislinkt.jobofferservice.DTO.JobOfferDTO;
import com.xwsdislinkt.jobofferservice.Model.JobOffer;
import com.xwsdislinkt.jobofferservice.Service.JobOfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "api/joboffers")
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<JobOfferDTO> save(@RequestBody JobOfferDTO dto, @RequestHeader String authorization){
        String userId = jobOfferService.findUserByApiKey(authorization);
        if(userId == "") {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var jobOffer = modelMapper.map(dto, JobOffer.class);
        jobOffer.setPublisherId(userId);
        jobOffer = jobOfferService.save(jobOffer);
        return new ResponseEntity<>(modelMapper.map(jobOffer, JobOfferDTO.class), HttpStatus.CREATED);
    }
}
