package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Activity;
import com.mastercode.fitmaster.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }
}
