package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trainer")
@Tag(name = "Trainer Controller", description = "REST APIs related to Trainer Entity")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @GetMapping
    @Operation(summary = "Get all trainers")
    public List<Trainer> getAll() {
        return trainerService.getAll();
    }
}
