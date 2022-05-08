package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.DTO.ExperienceDTO;
import com.xwsdislinkt.userservice.Model.Experience;
import com.xwsdislinkt.userservice.Service.ExperienceService;
import com.xwsdislinkt.userservice.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/experiences")
public class ExperienceController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ExperienceService experienceService;

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

    @PutMapping
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
}
