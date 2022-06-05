package com.example.agent_api.Controller;

import com.example.agent_api.DTO.JobOfferDTO;
import com.example.agent_api.Model.JobOffer;
import com.example.agent_api.Service.JobOfferService;
import com.example.agent_api.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/job-offers")
public class JobOfferController {
    @Autowired
    JobOfferService jobOfferService;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<JobOfferDTO> createNewOffer(@RequestBody JobOfferDTO dto) {
        var offer = modelMapper.map(dto, JobOffer.class);
        return new ResponseEntity<>(modelMapper.map(
                jobOfferService.save(offer), JobOfferDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobOffer>> getAll() {
        return new ResponseEntity<>(jobOfferService.findAll(), HttpStatus.OK);
    }
}
