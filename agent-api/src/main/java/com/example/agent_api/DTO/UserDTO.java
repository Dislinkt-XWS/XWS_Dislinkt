package com.example.agent_api.DTO;

import com.example.agent_api.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
