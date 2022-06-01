package com.example.agent_api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "agent_api_comments")
public class Comment {
    @Id
    public String id;
    public String userId;
    public String companyId;
    public String jobOfferId;
    public String textContent;
    public LocalDateTime datePosted;
}
