package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer getByTrainerID(Long id);
}
