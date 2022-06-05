package com.xwsdislinkt.jobofferservice.DTO;

import lombok.*;

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
    public String publisherId;
    public String companyId;
    public Boolean isPublished;
}
