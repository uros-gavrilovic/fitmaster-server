package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.TrainerEntity;
import com.mastercode.fitmaster.service.TrainerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The TrainerController class handles HTTP requests related to fitness trainers.
 *
 * @author Uroš Gavrilović
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {

    /**
     * Represents service class for entity TrainerEntity.
     */
    @Autowired
    private TrainerService trainerService;

    /**
     * Retrieves a list of all fitness trainers.
     *
     * @return A ResponseEntity containing a list of TrainerEntity objects and a status code of OK (200).
     */
    @GetMapping
    public ResponseEntity<List<TrainerEntity>> getAll() {
        return new ResponseEntity<>(trainerService.getAll(), HttpStatus.OK);
    }

    /**
     * Retrieves a list of TrainerDTO objects containing summarized information about fitness trainers.
     *
     * @return A ResponseEntity containing a list of TrainerDTO objects and a status code of OK (200).
     */
    @GetMapping("/dto")
    public ResponseEntity<List<TrainerDTO>> getAllDTOs() {
        return new ResponseEntity<>(trainerService.getAllDTOs(), HttpStatus.OK);
    }

    /**
     * Retrieves a fitness trainer by their unique ID.
     *
     * @param id The unique ID of the trainer to retrieve.
     *
     * @return A ResponseEntity containing the retrieved TrainerEntity object and a status code of OK (200),
     * or a 404 status code if the trainer is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TrainerEntity> getByID(@NotEmpty @PathVariable Long id) {
        TrainerEntity foundTrainerEntity = trainerService.findByID(id);
        if (foundTrainerEntity != null) {
            return new ResponseEntity<>(foundTrainerEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates an existing fitness trainerEntity's information.
     *
     * @param trainerEntity The TrainerEntity object representing the updated trainerEntity information.
     *
     * @return A ResponseEntity containing the updated TrainerEntity object and a status code of OK (200),
     * or a 404 status code if the trainerEntity is not found.
     *
     * @throws MethodArgumentNotValidException If there's an issue validating the TrainerEntity object.
     */
    @PutMapping
    public ResponseEntity<TrainerEntity> updateTrainer(@Valid @RequestBody TrainerEntity trainerEntity) {
        TrainerEntity updatedTrainerEntity = trainerService.update(trainerEntity);
        if (updatedTrainerEntity != null) {
            return new ResponseEntity<>(updatedTrainerEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a fitness trainer by their unique ID.
     *
     * @param id The unique ID of the trainer to delete.
     *
     * @return A ResponseEntity with a status code of NO_CONTENT (204) if the trainer was successfully deleted,
     * or a 404 status code if the trainer is not found.
     *
     * @throws MethodArgumentNotValidException If trainer's ID is empty or null.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<TrainerEntity> deleteTrainer(@NotEmpty @PathVariable Long id) {
        trainerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Changes the password of a fitness trainer.
     *
     * @param jsonRequest The JSON request containing the trainer's ID, old password, and new password.
     *
     * @return A ResponseEntity containing the updated TrainerEntity object with a status code of OK (200),
     * or a 404 status code if the trainer is not found or the old password is incorrect.
     *
     * @throws JsonProcessingException If there's an issue processing the JSON request.
     */
    @PostMapping("/change-password")
    public ResponseEntity<TrainerEntity> changePassword(@RequestBody String jsonRequest)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonRequest);

        Long trainerID = jsonNode.get("trainerID").asLong();
        String oldPassword = jsonNode.get("oldPassword").asText();
        String newPassword = jsonNode.get("newPassword").asText();

        TrainerEntity updatedTrainerEntity = trainerService.changePassword(trainerID, oldPassword, newPassword);
        if (updatedTrainerEntity != null) {
            return new ResponseEntity<>(updatedTrainerEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
