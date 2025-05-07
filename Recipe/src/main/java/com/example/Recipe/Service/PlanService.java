package com.example.springmongoproject.Service;

import com.example.springmongoproject.Model.Plan;

import java.util.List;

public interface PlanService {
    String savePlan(Plan plan);
    List<Plan> findAllPlan();
    Plan updatePlan(String planId, Plan updatedPlan);
    void deletePlan(String planId);
    Plan getPlanById(String planId);
}
