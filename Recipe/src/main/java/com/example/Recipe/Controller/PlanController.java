package com.example.springmongoproject.Controller;

import com.example.springmongoproject.Model.Plan;
import com.example.springmongoproject.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/plan")
@CrossOrigin("http://localhost:3000")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/save")
    public String savePlan(@RequestBody Plan plan) {
        return planService.savePlan(plan);
    }
    @GetMapping("/all")
    public List<Plan> getAllPlan() {
        return planService.findAllPlan();
    }

    @GetMapping("/get/{planId}")
    public Plan getPlanById(@PathVariable String planId) {
        return planService.getPlanById(planId);
    }



    @PutMapping("/update/{planId}")
    public Plan updatePlan(@PathVariable String planId, @RequestBody Plan updatedPlan) {
        return planService.updatePlan(planId, updatedPlan);
    }

    @DeleteMapping("/delete/{planId}")
    public String deletePlan(@PathVariable String planId) {
        planService.deletePlan(planId);
        return "Plan with ID " + planId + " deleted successfully!";
    }

}
