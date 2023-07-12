package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan getByPlanID(Long id);
}

