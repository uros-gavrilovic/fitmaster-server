package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.service.PlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/plan")
@Tag(name = "Plan Controller", description = "REST APIs related to Plan Entity")
public class PlanController {

    @Autowired
    private PlanService planService;

}
