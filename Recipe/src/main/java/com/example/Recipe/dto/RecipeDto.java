package com.example.Recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeDto {

    private String id;
    private String recipeName;
    private String recipeDescription;
    private String tips;
    private String file;
}
