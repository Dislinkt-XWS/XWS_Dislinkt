package com.example.agent_api.DTO;

import com.example.agent_api.Model.Enumerations.ExperienceLevel;
import com.example.agent_api.Model.Enumerations.ExperienceType;
import com.example.agent_api.Model.Enumerations.WorkPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String id;
    private String userId;
    private String companyId;
    private String textContent;
    private WorkPosition workPosition;
    private ExperienceLevel experienceLevel;
    private ExperienceType experienceType;
}
