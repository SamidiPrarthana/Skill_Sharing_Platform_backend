package com.example.Recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private String commentId;
    private String commentText;
    private String recipeId;
    private int likes;
    private String userId; // To track who made the comment
    private String timestamp; // When the comment was made
} 