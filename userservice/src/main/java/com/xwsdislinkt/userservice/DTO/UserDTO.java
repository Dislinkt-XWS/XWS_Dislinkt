package com.xwsdislinkt.userservice.DTO;

import com.xwsdislinkt.userservice.Model.Experience;
import com.xwsdislinkt.userservice.Model.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String phoneNumber;
    private UserGender userGender;
    private String fullName;
    private Date dateOfBirth;
    private String bio;
    private Boolean isPrivate;
    private List<Experience> workExperience;
    private List<Experience> education;
    private List<String> skills;
    private List<String> interests;
    //private List<Long> blockedUsers;
    //private List<Long> followedUsers; No need


}
