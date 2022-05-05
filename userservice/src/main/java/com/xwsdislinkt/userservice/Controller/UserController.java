package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.DTO.*;
import com.xwsdislinkt.userservice.Model.Experience;
import com.xwsdislinkt.userservice.Model.Interest;
import com.xwsdislinkt.userservice.Model.Skill;
import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.ExperienceService;
import com.xwsdislinkt.userservice.Service.InterestService;
import com.xwsdislinkt.userservice.Service.SkillService;
import com.xwsdislinkt.userservice.Service.UserService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ExperienceService experienceService;
    @Autowired
    SkillService skillService;
    @Autowired
    InterestService interestService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Validated UserDTO dto){
        var user = modelMapper.map(dto, User.class);
        return new ResponseEntity<>(modelMapper.map(
                userService.save(user), UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody @Validated UserDTO dto) {

        if(userService.get(dto.getId()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.get(dto.getId()).get();
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUserGender(dto.getUserGender());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setBio(dto.getBio());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return new ResponseEntity<>(modelMapper.map(
                userService.update(user), UserDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> get(){
        var users = userService.findAll();
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Validated LoginDTO dto) {
        var user = userService.findByUsername(dto.getUsername());
        if (user == null || !user.getPassword().equals(dto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/follow/approve" )
    public ResponseEntity<Boolean> approveFollow(@RequestBody Map<String, String> userIds){
        if(userService.approveFollow(userIds.get("userId"), userIds.get("followerId"))){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/follow")
    public ResponseEntity<Boolean> follow(@RequestBody Map<String, String> userIds){
        System.out.println(userIds.get("userId"));
        System.out.println(userIds.get("toFollowUser"));

        if(userService.followUser(userIds.get("userId"), userIds.get("toFollowUser"))){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/education")
    public ResponseEntity<ExperienceDTO> addEducation(@RequestBody @Validated ExperienceDTO dto) {
        var experience = modelMapper.map(dto, Experience.class);

        if(userService.get(experience.getUserId()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var user = userService.get(experience.getUserId()).get();
        System.out.println(user.getUsername());
        experience = experienceService.save(experience);
        user.getEducation().add(experience.getId());
        userService.update(user);

        return new ResponseEntity<>(modelMapper.map(experience, ExperienceDTO.class), HttpStatus.CREATED);
    }

    @PostMapping(value = "/work")
    public ResponseEntity<ExperienceDTO> addWorkExperience(@RequestBody @Validated ExperienceDTO dto) {
        var experience = modelMapper.map(dto, Experience.class);
        var user = userService.get(experience.getUserId()).get();

        experience = experienceService.save(experience);
        user.getWorkExperience().add(experience.getId());
        userService.update(user);

        return new ResponseEntity<>(modelMapper.map(experience, ExperienceDTO.class), HttpStatus.CREATED);
    }

    @PostMapping(value = "/skill")
    public ResponseEntity<SkillDTO> addSkill(@RequestBody @Validated SkillDTO dto) {
        var skill = modelMapper.map(dto, Skill.class);
        var user = userService.get(skill.getUserId()).get();

        skill = skillService.save(skill);
        user.getSkills().add(skill.getId());
        userService.update(user);

        return new ResponseEntity<>(modelMapper.map(skill, SkillDTO.class), HttpStatus.CREATED);
    }

    @PostMapping(value = "/interest")
    public ResponseEntity<InterestDTO> addInterest(@RequestBody @Validated InterestDTO dto) {
        var interest = modelMapper.map(dto, Interest.class);
        var user = userService.get(interest.getUserId()).get();

        interest = interestService.save(interest);
        user.getInterests().add(interest.getId());
        userService.update(user);

        return new ResponseEntity<>(modelMapper.map(interest, InterestDTO.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<ExperienceDTO> editExperience(@RequestBody @Validated ExperienceDTO dto) {

        if(experienceService.get(dto.getId()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Experience experience = experienceService.get(dto.getId()).get();

        experience.setStart(dto.getStart());
        experience.setEnd(dto.getEnd());
        experience.setRole(dto.getRole());
        experience.setEstablishmentName(dto.getEstablishmentName());

        return new ResponseEntity<>(modelMapper.map(
                experienceService.update(experience), ExperienceDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/education/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable String id) {

        if(experienceService.get(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var experience = experienceService.get(id).get();
        var user = userService.get(experience.getUserId()).get();
        experienceService.delete(id);
        user.getEducation().remove(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/work/{id}")
    public ResponseEntity<Void> deleteWorkExperience(@PathVariable String id) {

        if(experienceService.get(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var experience = experienceService.get(id).get();
        var user = userService.get(experience.getUserId()).get();
        experienceService.delete(id);
        user.getWorkExperience().remove(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/skill/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable String id) {

        if(skillService.get(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var skill = skillService.get(id).get();
        var user = userService.get(skill.getUserId()).get();
        skillService.delete(id);
        user.getSkills().remove(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/interest/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable String id) {

        if(interestService.get(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var interest = interestService.get(id).get();
        var user = userService.get(interest.getUserId()).get();
        interestService.delete(id);
        user.getInterests().remove(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
