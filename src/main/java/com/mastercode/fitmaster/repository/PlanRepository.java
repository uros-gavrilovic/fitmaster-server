package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan getByPlanID(Long id);

    @Query("SELECT p FROM Plan p WHERE p.trainer.id = :trainerId")
    public Set<Plan> findByTrainerId(@Param("trainerId") Long trainerId);

}

