package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.Configuration.Security.TokenUtils;
import com.xwsdislinkt.userservice.DTO.*;
import com.xwsdislinkt.userservice.Model.Experience;
import com.xwsdislinkt.userservice.Model.Interest;
import com.xwsdislinkt.userservice.Model.Skill;
import com.xwsdislinkt.userservice.Model.User;
import com.xwsdislinkt.userservice.Service.ExperienceService;
import com.xwsdislinkt.userservice.Service.InterestService;
import com.xwsdislinkt.userservice.Service.SkillService;
import com.xwsdislinkt.userservice.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users")
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
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenUtils tokenUtils;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated UserDTO dto){
        if(userService.findByUsernameOrEmail(dto.getEmail()) != null) {
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.BAD_REQUEST);
        }

        if(userService.findByUsernameOrEmail(dto.getUsername()) != null) {
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.BAD_REQUEST);
        }

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

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTokenState> login(@RequestBody LoginDTO dto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsernameOrEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = (User) authentication.getPrincipal();
        var jwt = tokenUtils.generateToken(user.getUsername());
        var expiresIn = tokenUtils.getExpiredIn();

        return new ResponseEntity<>(new UserTokenState(jwt, expiresIn), HttpStatus.OK);
    }

    @GetMapping(value = "/loggedinandfollowers")
    public ResponseEntity<List<String>> newsfeedGet(){

        var loggedInUser = userService.findLoggedInUser();
        System.out.println(loggedInUser.getFullName());
        List<String> allUsers = new ArrayList<>();
        allUsers = loggedInUser.getFollowedUsers();
        System.out.println(allUsers);
        System.out.println("ZASTO NULL POINTER PICKA TI MATERINAAAAAAA");
        allUsers.add(loggedInUser.getId());
        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @PostMapping(value = "/follow/approve" )
    public ResponseEntity<String> approveFollow(@RequestBody Map<String, String> userToApproveIds){
        var user = userService.findLoggedInUser();

        if(user == null){
            return new ResponseEntity<>("User must be logged in to approve a follow", HttpStatus.BAD_REQUEST);
        }
        if(userService.approveFollow(user.getId(), userToApproveIds.get("userToApproveIds"))){
            return new ResponseEntity<>("Successfully approved follow ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldnt execture the approve follow operation.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/follow")
    public ResponseEntity<String> follow(@RequestBody Map<String, String> userToFollowId){
        var user = userService.findLoggedInUser();
        System.out.println("Controller logged in user id: " + user.getId());
        System.out.println("Controller user to follow id: " +userToFollowId);
        if(user == null){
            return new ResponseEntity<>("User must be logged in to follow other accounts", HttpStatus.BAD_REQUEST);
        }

        if(userService.followUser(user.getId(), userToFollowId.get("userToFollowId"))){
            return new ResponseEntity<>("Successfully followed user. ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldnt execute the follow operation.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/search/{criteria}")
    public ResponseEntity<List<UserDTO>> search(@PathVariable("criteria") String criteria) {
        var users = userService.searchUsers(criteria);
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
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
