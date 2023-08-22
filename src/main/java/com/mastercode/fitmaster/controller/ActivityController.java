package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.Activity;
import com.mastercode.fitmaster.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The ActivityController class handles HTTP requests related to fitness activities.
 */
@RestController
@RequestMapping(value = "/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * Retrieves a list of all fitness activities.
     *
     * @return A list of Activity objects representing fitness activities.
     */
    @GetMapping
    public List<Activity> getAll() {
        return activityService.getAll();
    }
}
