package com.example.springmongoproject.Repo;

import com.example.springmongoproject.Model.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepo extends MongoRepository<Plan, String> {
}
