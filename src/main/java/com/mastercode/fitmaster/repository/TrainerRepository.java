package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
    TrainerEntity getByTrainerID(Long id);
    Optional<TrainerEntity> findByUsername(String username);
}
