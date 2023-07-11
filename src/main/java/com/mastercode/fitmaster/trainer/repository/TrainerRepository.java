package com.mastercode.fitmaster.trainer.repository;

import com.mastercode.fitmaster.trainer.model.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

}
