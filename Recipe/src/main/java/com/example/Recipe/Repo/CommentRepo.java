package com.example.Recipe.Repo;

import com.example.Recipe.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CommentRepo extends MongoRepository<Comment, String> {
    List<Comment> findByRecipeId(String recipeId);
}