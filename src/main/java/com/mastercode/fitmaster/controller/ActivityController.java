package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.ActivityEntity;
import com.mastercode.fitmaster.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The ActivityController class handles HTTP requests related to fitness activities.
 *
 * @author Uroš Gavrilović
 */
@RestController
@RequestMapping(value = "/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * Retrieves a list of all fitness activities.
     *
     * @return A list of ActivityEntity objects representing fitness activities.
     */
    @GetMapping
    public List<ActivityEntity> getAll() {
        return activityService.getAll();
    }
}
