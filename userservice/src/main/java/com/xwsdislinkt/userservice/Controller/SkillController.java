package com.xwsdislinkt.userservice.Controller;

import com.xwsdislinkt.userservice.DTO.SkillDTO;
import com.xwsdislinkt.userservice.Model.Skill;
import com.xwsdislinkt.userservice.Service.SkillService;
import com.xwsdislinkt.userservice.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<SkillDTO> addSkill(@RequestBody @Validated SkillDTO dto) {
        var user = userService.findLoggedInUser();
        dto.setUserId(user.getId());
        var skill = modelMapper.map(dto, Skill.class);

        skill = skillService.save(skill);
        user.getSkills().add(skill.getId());
        userService.update(user);

        return new ResponseEntity<>(modelMapper.map(skill, SkillDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable String id) {

        if(skillService.get(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var skill = skillService.get(id).get();
        var user = userService.findLoggedInUser();
        skillService.delete(id);
        user.getSkills().remove(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getSkillsForUser(@PathVariable String id) {
        var user = userService.get(id).get();

        if (user == null)
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);

        var skills = skillService.findAllByUserId(id);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}
