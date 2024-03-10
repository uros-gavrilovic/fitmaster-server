package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    ExerciseEntity getByExerciseID(Long id);
}
