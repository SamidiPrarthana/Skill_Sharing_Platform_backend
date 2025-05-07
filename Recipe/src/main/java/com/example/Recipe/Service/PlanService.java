package com.example.Recipe.Service;

import com.example.Recipe.entity.Plan;

import java.util.List;

public interface PlanService {
    String savePlan(Plan plan);
    List<Plan> findAllPlan();
    Plan updatePlan(String planId, Plan updatedPlan);
    void deletePlan(String planId);
    Plan getPlanById(String planId);
}
