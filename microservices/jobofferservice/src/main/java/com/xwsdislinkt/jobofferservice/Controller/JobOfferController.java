package com.xwsdislinkt.jobofferservice.Controller;

import com.xwsdislinkt.jobofferservice.DTO.JobOfferDTO;
import com.xwsdislinkt.jobofferservice.Service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "api/joboffers")
public class JobOfferController {
    @Autowired
    JobOfferService jobOfferService;

    @PostMapping
    public ResponseEntity<JobOfferDTO> postedFromAgent(){
        System.out.println("Got pinged from agent api");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
