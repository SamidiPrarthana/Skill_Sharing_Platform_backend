package com.example.Recipe.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comment")
public class Comment {
    @Id
    private String commentId;
    private String commentText;
    private String recipeId;
    private int likes;
    private String userId;
    private LocalDateTime timestamp;
    private boolean liked; // Changed from isLiked to liked to match getter/setter naming
}