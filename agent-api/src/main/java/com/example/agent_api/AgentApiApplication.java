package com.example.agent_api;

import com.example.agent_api.Model.Role;
import com.example.agent_api.Model.User;
import com.example.agent_api.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class AgentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialize(UserRepository userRepository) {
        return args -> {
            var user = new User(UUID.randomUUID().toString(), "andjela", "$2a$10$FAecEzDaGoRjIQ/a4OyPxeeGiePlwsJ8XqVWEMAsD1BUDqmbW2/p.",
                    "andjela.ra28@gmail.com", "Andjela", "Djuric", new Role("ADMIN"), "");

            if (userRepository.getByUsername(user.getUsername()) == null)
                userRepository.save(user);
        };
    }

}
