package com.example.Recipe.Service;

import com.example.Recipe.dto.CommentDto;
import com.example.Recipe.entity.Comment;
import com.example.Recipe.Repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public Comment addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCommentText(commentDto.getCommentText());
        comment.setRecipeId(commentDto.getRecipeId());
        comment.setUserId(commentDto.getUserId());
        comment.setLikes(0);
        comment.setTimestamp(LocalDateTime.now());
        comment.setLiked(false);
        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsByRecipeId(String recipeId) {
        return commentRepo.findByRecipeId(recipeId);
    }

    public Comment likeComment(String commentId, String userId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        
        // Toggle like status
        if (comment.isLiked()) {
            comment.setLikes(comment.getLikes() - 1);
            comment.setLiked(false);
        } else {
            comment.setLikes(comment.getLikes() + 1);
            comment.setLiked(true);
        }
        
        return commentRepo.save(comment);
    }

    public List<Comment> findAllComments() {
        try {
            List<Comment> comments = commentRepo.findAll();
            System.out.println("Fetched comments: " + comments);
            return comments;
        } catch (Exception e) {
            System.err.println("Error fetching comments: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Comment updateComment(String commentId, Comment updatedComment) {
        return commentRepo.findById(commentId).map(existingComment -> {
            existingComment.setCommentText(updatedComment.getCommentText());
            return commentRepo.save(existingComment);
        }).orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
    }

    public void deleteComment(String commentId) {
        if (commentRepo.existsById(commentId)) {
            commentRepo.deleteById(commentId);
        } else {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
    }
}
