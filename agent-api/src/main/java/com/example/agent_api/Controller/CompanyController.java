package com.example.agent_api.Controller;

import com.example.agent_api.DTO.CompanyDTO;
import com.example.agent_api.Model.Company;
import com.example.agent_api.Model.Enumerations.Status;
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
    public ResponseEntity<?> createNewCompany(@RequestBody CompanyDTO dto) {
        var currentUser = userService.findLoggedInUser();

        if (currentUser == null)
            return new ResponseEntity("No user is logged in!", HttpStatus.BAD_REQUEST);

        var company = modelMapper.map(dto, Company.class);
        return new ResponseEntity<>(modelMapper.map(
                companyService.save(company), CompanyDTO.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> approveNewCompanyRequest(@PathVariable String id, @RequestBody Status status) {
        var company = companyService.get(id).get();
        if (company == null)
            return new ResponseEntity<>("Company with this id doesn't exist!", HttpStatus.NOT_FOUND);

        var updated = companyService.approveCompanyRequest(id, status);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCompanyInformation(@RequestBody CompanyDTO dto) {
        var user = userService.findLoggedInUser();
        if (!user.getId().equals(dto.getOwnerId()))
            return new ResponseEntity<>("Company doesn't belong to the current user!", HttpStatus.BAD_REQUEST);

        var company = companyService.get(dto.getId()).get();
        if (company == null)
            return new ResponseEntity<>("Company with this id doesn't exist!", HttpStatus.NOT_FOUND);

        if (dto.getDescription() != null && !dto.getDescription().isEmpty())
            company.setDescription(dto.getDescription());
        if (dto.getAddress() != null && !dto.getAddress().isEmpty())
            company.setAddress(dto.getAddress());
        if (dto.getCity() != null && !dto.getCity().isEmpty())
            company.setCity(dto.getCity());
        if (dto.getCountry() != null && !dto.getCountry().isEmpty())
            company.setCountry(dto.getCountry());
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isEmpty())
            company.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getYearOfEstablishment() != 0 && !String.valueOf(dto.getYearOfEstablishment()).isEmpty())
            company.setYearOfEstablishment(dto.getYearOfEstablishment());

        return new ResponseEntity<>(companyService.update(company), HttpStatus.OK);
    }
}
