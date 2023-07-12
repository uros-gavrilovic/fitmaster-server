package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity getByOrdinalNumber(Integer ordinalNumber);
}
