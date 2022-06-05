package com.example.agent_api.Model;

import com.example.agent_api.Model.Enumerations.ExperienceLevel;
import com.example.agent_api.Model.Enumerations.ExperienceType;
import com.example.agent_api.Model.Enumerations.WorkPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "agent_api_comments")
public class CompanyExperience {
    @Id
    private String id;
    private String userId;
    private String companyId;
    private String textContent;
    private WorkPosition workPosition;
    private ExperienceLevel experienceLevel;
    private double salary;
    private ExperienceType experienceType;
    private Date datePosted;

}
