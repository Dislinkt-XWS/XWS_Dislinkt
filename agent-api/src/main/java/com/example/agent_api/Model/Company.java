package com.example.agent_api.Model;

import com.example.agent_api.Model.Enumerations.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "companies")
public class Company {
    @Id
    public String id;
    public String ownerId;
    public String name;
    public String description;
    public int yearOfEstablishment;
    public String email;
    public String phoneNumber;
    public String address;
    public String city;
    public String country;
    public Status status;

    private List<String> comments;
    private List<String> jobOffers;
}
