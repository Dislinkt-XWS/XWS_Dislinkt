package com.xwsdislinkt.userservice.DTO;

import com.xwsdislinkt.userservice.Model.Experience;
import com.xwsdislinkt.userservice.Model.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private String id;
    private String password;
    private String username;
    private String email;
    private String phoneNumber;
    private UserGender userGender;
    private String fullName;
    private LocalDateTime dateOfBirth;
    private String bio;
    private Boolean isPrivate;
    private List<String> workExperience;
    private List<String> education;
    private List<String> skills;
    private List<String> interests;
    //private List<Long> blockedUsers;
    //private List<Long> followedUsers; No need

}
