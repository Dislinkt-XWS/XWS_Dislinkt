package com.example.agent_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
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
}
