package com.xwsdislinkt.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private UserGender userGender;
    private String fullName;
    private LocalDateTime dateOfBirth;
    private String bio;
    private Boolean isPrivate;
    private List<Long> blockedUsers;
    private List<Experience> workExperience;
    private List<Experience> education;
    private List<Long> followedUsers;
    private List<String> interests;
    private List<String> skills;
}
