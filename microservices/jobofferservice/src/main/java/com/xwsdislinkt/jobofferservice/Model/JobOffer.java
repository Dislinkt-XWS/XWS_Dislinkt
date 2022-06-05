package com.xwsdislinkt.jobofferservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "job_offers")
public class JobOffer {
    @Id
    public String id;
    public String position;
    public String jobDescription;
    public String requirements;
    public String publisherId;
    public String companyId;
    public Boolean isPublished;

}
