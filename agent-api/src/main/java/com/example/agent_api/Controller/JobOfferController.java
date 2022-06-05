package com.example.agent_api.Controller;

import com.example.agent_api.DTO.JobOfferDTO;
import com.example.agent_api.Model.JobOffer;
import com.example.agent_api.Service.JobOfferService;
import com.example.agent_api.Service.UserService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/joboffer")
public class JobOfferController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JobOfferService jobOfferService;

    @PostMapping
    public ResponseEntity<?> saveJobOffer(@RequestBody @Validated JobOfferDTO dto){
        if(jobOfferService.get(dto.getId()).isPresent()){
            return new ResponseEntity<>("Job offer already exists there has been a database error.", HttpStatus.BAD_REQUEST);
        }

        var offer = modelMapper.map(dto, JobOffer.class);
        if(offer.getIsPublished()){
            //Ovde ide kod za slanje na pravljenje EP u user service
        }
        return new ResponseEntity<>(modelMapper.map(
                jobOfferService.save(offer), JobOfferDTO.class), HttpStatus.CREATED);
    }
}
