package com.example.agent_api.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobOfferDTO {
    public String id;
    public String position;
    public String jobDescription;
    public String requirements;
    public String companyId;

    public Boolean isPublished;
    private List<String> comments;
}
