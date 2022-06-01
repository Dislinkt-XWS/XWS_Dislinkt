package com.example.agent_api.Controller;

import com.example.agent_api.DTO.CompanyDTO;
import com.example.agent_api.Model.Company;
import com.example.agent_api.Model.User;
import com.example.agent_api.Service.CompanyService;
import com.example.agent_api.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getAllCompanies() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewCompany(CompanyDTO dto) {
        var currentUser = userService.findLoggedInUser();

        if (currentUser == null)
            return new ResponseEntity("No user is logged in!", HttpStatus.BAD_REQUEST);

        var company = modelMapper.map(dto, Company.class);
        return new ResponseEntity<>(companyService.save(company), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> approveNewCompanyRequest(@PathVariable String id, @RequestBody boolean status) {
        var company = companyService.get(id).get();
        if (company == null)
            return new ResponseEntity<>("Company with this id doesn't exist!", HttpStatus.NOT_FOUND);

        companyService.approveCompanyRequest(id, status);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
