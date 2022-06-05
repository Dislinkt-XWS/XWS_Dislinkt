package com.example.agent_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDTO {
    public String id;
    public String position;
    public String jobDescription;
    public String requirements;
    public String companyId;
}
