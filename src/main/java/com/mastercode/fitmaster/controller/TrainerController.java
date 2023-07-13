package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<Trainer>> getAll() {
        return new ResponseEntity<>(trainerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getByID(@PathVariable Long id) {
        return new ResponseEntity<>(trainerService.findByID(id), HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<TrainerDTO>> getAllDTOs() {
        return new ResponseEntity<>(trainerService.getAllDTOs(), HttpStatus.OK);
    }
}
