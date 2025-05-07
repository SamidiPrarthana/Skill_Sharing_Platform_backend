package com.example.Recipe.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "plan")
@Getter
public class Plan {
    @Id
    String planId;
    String planTitle;
    String planMainIngredients;
    String planDescription;
    String planStartDate;
    String planEndDate;
    String planDifficulty;
    String planCategory;


}
