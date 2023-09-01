package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer getByTrainerID(Long id);

    @Query("select t from Trainer t where t.username = ?1")
    Optional<Trainer> findByUsername(String username);
}
