package com.example.agent_api.DTO;

import com.example.agent_api.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    @NotNull(message = "User must have a username!")
    private String username;
    @NotNull(message = "User must have a password!")
    private String password;
    @NotNull(message = "User must have an email!")
    @Email(message = "Please enter the correct email format!")
    private String email;
    @NotNull(message = "User must have first name!")
    private String firstName;
    @NotNull(message = "User must have last name!")
    private String lastName;
}
