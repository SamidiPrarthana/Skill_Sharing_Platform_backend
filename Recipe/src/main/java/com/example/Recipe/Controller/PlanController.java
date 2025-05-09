package com.example.Recipe.Controller;

import com.example.Recipe.entity.Plan;
import com.example.Recipe.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/plan")
@CrossOrigin("http://localhost:3000")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/save")
    public ResponseEntity<?> savePlan(@RequestBody Plan plan) {
        try {
            if (plan.getPlanTitle() == null || plan.getPlanTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Plan title is required.");
            }
            String savedId = planService.savePlan(plan);
            return ResponseEntity.status(HttpStatus.CREATED).body("Plan saved with ID: " + savedId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving plan: " + e.getMessage());
        }
    }
    // GET all plans
    @GetMapping("/all")
    public ResponseEntity<?> getAllPlan() {
        try {
            List<Plan> plans = planService.findAllPlan();
            return ResponseEntity.ok(plans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching plans: " + e.getMessage());
        }
    }

    // GET plan by ID
    @GetMapping("/get/{planId}")
    public ResponseEntity<?> getPlanById(@PathVariable String planId) {
        try {
            Plan plan = planService.getPlanById(planId);
            return ResponseEntity.ok(plan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving plan: " + e.getMessage());
        }
    }

    // UPDATE a plan
    @PutMapping("/update/{planId}")
    public ResponseEntity<?> updatePlan(@PathVariable String planId, @RequestBody Plan updatedPlan) {
        try {
            if (updatedPlan.getPlanTitle() == null || updatedPlan.getPlanTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Plan title is required for update.");
            }
            Plan updated = planService.updatePlan(planId, updatedPlan);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating plan: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{planId}")
    public String deletePlan(@PathVariable String planId) {
        planService.deletePlan(planId);
        return "Plan with ID " + planId + " deleted successfully!";
    }

}
