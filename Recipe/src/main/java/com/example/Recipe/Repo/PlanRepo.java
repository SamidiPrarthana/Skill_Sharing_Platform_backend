package com.example.Recipe.Repo;

import com.example.Recipe.entity.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepo extends MongoRepository<Plan, String> {
}
