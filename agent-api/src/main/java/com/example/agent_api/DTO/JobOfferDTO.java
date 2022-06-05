package com.example.agent_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferDTO {
    public String id;
    public String position;
    public String jobDescription;
    public String requirements;
    public Boolean isPublished;
    private List<String> comments;
}
