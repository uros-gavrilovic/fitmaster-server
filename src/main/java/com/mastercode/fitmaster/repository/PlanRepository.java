package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
    PlanEntity getByPlanID(Long id);

    Set<PlanEntity> findByTrainerEntity_TrainerID(@Param("trainerId") Long trainerId);

    Set<PlanEntity> findByMemberEntity_MemberID(@Param("memberId") Long memberId);
}

