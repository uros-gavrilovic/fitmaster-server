package com.mastercode.fitmaster.trainer.controller;

import com.mastercode.fitmaster.trainer.service.TrainerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/trainer")
@Tag(name = "Trainer Controller", description = "REST APIs related to Trainer Entity")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

}
