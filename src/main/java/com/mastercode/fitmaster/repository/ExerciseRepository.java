package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise getByExerciseID(Long id);
}
