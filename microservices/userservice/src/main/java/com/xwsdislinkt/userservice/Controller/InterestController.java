package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.DTO.InterestDTO;
import com.xwsdislinkt.userservice.Model.Interest;
import com.xwsdislinkt.userservice.Service.InterestService;
import com.xwsdislinkt.userservice.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<InterestDTO> addInterest(@RequestBody @Validated InterestDTO dto) {
        var user = userService.findLoggedInUser();
        dto.setUserId(user.getId());

        var interest = modelMapper.map(dto, Interest.class);
        interest = interestService.save(interest);
        user.getInterests().add(interest.getId());
        userService.update(user);

        return new ResponseEntity<>(modelMapper.map(interest, InterestDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable String id) {

        if(interestService.get(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var interest = interestService.get(id).get();
        var user = userService.findLoggedInUser();
        interestService.delete(id);
        user.getInterests().remove(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getInterestsByUser(@PathVariable String id) {
        var user = userService.get(id).get();

        if (user == null)
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);

        var interests = interestService.findAllByUserId(id);
        return new ResponseEntity<>(interests, HttpStatus.OK);
    }
}
