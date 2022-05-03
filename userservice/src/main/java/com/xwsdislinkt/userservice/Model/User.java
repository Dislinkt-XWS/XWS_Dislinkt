package com.xwsdislinkt.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String  id;
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
    private List<String > blockedUsers;
    private List<Experience> workExperience;
    private List<Experience> education;
    private List<String > followedUsers;
    private List<String> interests;
    private List<String> skills;
    //List of ids of users who sent a follow request to the user.
    private List<String > followRequests;
    //List of ids of follow requests the user made
    private List<String > pendingFollowRequests;
    //List of ids following the user
    private List<String > followers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
