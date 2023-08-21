package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;
    @Autowired
    private TrainerAdapter trainerAdapter;

    @GetMapping
    public ResponseEntity<List<Trainer>> getAll() {
        return new ResponseEntity<>(trainerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<TrainerDTO>> getAllDTOs() {
        return new ResponseEntity<>(trainerService.getAllDTOs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getByID(@PathVariable Long id) {
        return new ResponseEntity<>(trainerService.findByID(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Trainer> updateTrainer(@RequestBody Trainer trainer) {
        return new ResponseEntity<>(trainerService.update(trainer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trainer> deleteTrainer(@PathVariable Long id) {
        trainerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Trainer> changePassword(@RequestBody String jsonRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonRequest);

        Long trainerID = jsonNode.get("trainerID").asLong();
        String oldPassword = jsonNode.get("oldPassword").asText();
        String newPassword = jsonNode.get("newPassword").asText();

        Trainer updatedTrainer = trainerService.changePassword(trainerID, oldPassword, newPassword);
        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
    }
}
