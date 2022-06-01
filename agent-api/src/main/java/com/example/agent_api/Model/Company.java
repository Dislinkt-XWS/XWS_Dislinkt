package com.example.agent_api.Model;

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
    public String description;
    public int yearOfEstablishment;
    public String email;
    public String phoneNumber;
    public String address;
    public String city;
    public String country;
    public boolean isApproved;

    private List<String> comments;
    private List<String> jobOffers;

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
