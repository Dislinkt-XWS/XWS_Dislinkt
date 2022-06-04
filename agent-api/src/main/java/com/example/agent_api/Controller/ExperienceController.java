package com.example.agent_api.Controller;

import com.example.agent_api.DTO.CommentDTO;
import com.example.agent_api.Model.CompanyExperience;
import com.example.agent_api.Model.Enumerations.ExperienceType;
import com.example.agent_api.Service.CompanyExperienceService;
import com.example.agent_api.Service.CompanyService;
import com.example.agent_api.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/experience")
public class ExperienceController {
    @Autowired
    CompanyExperienceService experienceService;
    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> commentOnCompany(@RequestBody CommentDTO dto) {
        var comment = experienceService.save(modelMapper.map(dto, CompanyExperience.class));
        return new ResponseEntity<>(modelMapper.map(comment, CommentDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/companies/{id}/comments")
    public ResponseEntity<List<CompanyExperience>> getCommentsForCompany(@PathVariable String id) {
        return new ResponseEntity<>(experienceService.findCommentsByCompany(id), HttpStatus.OK);
    }

    @GetMapping(value = "/companies/{id}/salaries")
    public ResponseEntity<List<CompanyExperience>> getSalariesForCompany(@PathVariable String id) {
        return new ResponseEntity<>(experienceService.findSalariesByCompany(id), HttpStatus.OK);
    }

    @GetMapping(value = "/companies/{id}/interviews")
    public ResponseEntity<List<CompanyExperience>> getInterviewsForCompany(@PathVariable String id) {
        return new ResponseEntity<>(experienceService.findInterviewsByCompany(id), HttpStatus.OK);
    }
}
