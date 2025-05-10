package com.example.Recipe.Controller;

import com.example.Recipe.Service.CommentService;
import com.example.Recipe.dto.CommentDto;
import com.example.Recipe.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/comment")
@CrossOrigin(origins = "http://localhost:3000") // Or "*", for dev use only
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addComment(@RequestBody CommentDto commentDto) {
        try {
            Comment savedComment = commentService.addComment(commentDto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment added successfully");
            response.put("comment", savedComment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error adding comment: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllComments() {
        try {
            List<Comment> comments = commentService.findAllComments();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("comments", comments);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error fetching comments: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<Map<String, Object>> getCommentsByRecipeId(@PathVariable String recipeId) {
        try {
            List<Comment> comments = commentService.getCommentsByRecipeId(recipeId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("comments", comments);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error fetching comments: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<Map<String, Object>> updateComment(
            @PathVariable String commentId,
            @RequestBody Comment updatedComment) {
        try {
            Comment comment = commentService.updateComment(commentId, updatedComment);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment updated successfully");
            response.put("comment", comment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error updating comment: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable String commentId) {
        try {
            commentService.deleteComment(commentId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error deleting comment: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/like/{commentId}")
    public ResponseEntity<Map<String, Object>> likeComment(
            @PathVariable String commentId,
            @RequestParam String userId) {
        try {
            Comment updatedComment = commentService.likeComment(commentId, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment liked successfully");
            response.put("comment", updatedComment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error liking comment: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
