package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    ActivityEntity getByActivityID(Long activityID);
}
