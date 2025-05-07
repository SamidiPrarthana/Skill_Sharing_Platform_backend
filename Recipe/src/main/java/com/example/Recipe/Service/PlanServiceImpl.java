package com.example.springmongoproject.Service;

import com.example.springmongoproject.Model.Plan;
import com.example.springmongoproject.Repo.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepo planRepo;

    @Override
    public String savePlan(Plan plan){
        return planRepo.save(plan).getPlanId();
    }
    @Override
    public List<Plan> findAllPlan() {
        try {
            List<Plan> plans = planRepo.findAll();
            System.out.println("Fetched plans: " + plans);
            return plans;
        } catch (Exception e) {
            System.err.println("Error fetching plans: " + e.getMessage());
            return Collections.emptyList(); // Return empty list instead of failing
        }
    }

    @Override
    public Plan updatePlan(String planId, Plan updatedPlan) {
        return planRepo.findById(planId).map(existingPlan -> {
            existingPlan.setPlanTitle(updatedPlan.getPlanTitle());
            existingPlan.setPlanMainIngredients(updatedPlan.getPlanMainIngredients());
            existingPlan.setPlanDescription(updatedPlan.getPlanDescription());
            existingPlan.setPlanStartDate(updatedPlan.getPlanStartDate());
            existingPlan.setPlanEndDate(updatedPlan.getPlanEndDate());
            existingPlan.setPlanDifficulty(updatedPlan.getPlanDifficulty());
            existingPlan.setPlanCategory(updatedPlan.getPlanCategory());
            return planRepo.save(existingPlan);
        }).orElseThrow(() -> new RuntimeException("Plan not found with id: " + planId));
    }

    @Override
    public void deletePlan(String planId) {
        if (planRepo.existsById(planId)) {
            planRepo.deleteById(planId);
        } else {
            throw new RuntimeException("Plan not found with id: " + planId);
        }
    }
    @Override
    public Plan getPlanById(String planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + planId));
    }
}
