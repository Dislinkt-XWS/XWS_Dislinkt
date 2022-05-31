package com.example.agent_api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "agent_api_users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;

}
