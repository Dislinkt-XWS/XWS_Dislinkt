package com.example.agent_api.Controller;

import com.example.agent_api.DTO.JobOfferDTO;
import com.example.agent_api.Model.JobOffer;
import com.example.agent_api.Model.User;
import com.example.agent_api.Service.JobOfferService;
import com.example.agent_api.Service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<JobOfferDTO> createNewOffer(@RequestBody JobOfferDTO dto) {
        var offer = modelMapper.map(dto, JobOffer.class);
        var loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsernameOrEmail(loggedInUser);
        if(offer.getIsPublished()){
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", user.getDislinktApiKey());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<JobOfferDTO> entity = new HttpEntity<>(dto, headers);
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.exchange("http://localhost:8777/api/joboffers", HttpMethod.POST, entity, JobOfferDTO.class);
            if(response.getStatusCode().is4xxClientError()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(modelMapper.map(
                jobOfferService.save(offer), JobOfferDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobOffer>> getAll() {
        return new ResponseEntity<>(jobOfferService.findAll(), HttpStatus.OK);
    }
}




