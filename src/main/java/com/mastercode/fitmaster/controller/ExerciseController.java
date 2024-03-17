package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.ExerciseEntity;
import com.mastercode.fitmaster.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ExerciseController class handles HTTP requests related to exercises.
 *
 * @author Uroš Gavrilović
 */
@RestController
@RequestMapping(value = "/api/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseEntity> getAll() {
        return exerciseService.getAll();
    }

    /**
     * Retrieves a list of ExerciseDTO objects containing summarized information about exercises.
     *
     * @return A list of ExerciseDTO objects.
     */
    @GetMapping("/dto")
    public List<ExerciseDTO> getAllDTOs() {
        return exerciseService.getAllDTOs();
    }

    @GetMapping("/{id}")
    public ExerciseEntity getByID(@PathVariable Long id) {
        return exerciseService.findByID(id);
    }

    @PostMapping
    public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseEntity exerciseEntity) {
        return ResponseEntity.ok(exerciseService.create(exerciseEntity));
    }

    @PutMapping
    public ResponseEntity<ExerciseDTO> updateExercise(@RequestBody ExerciseEntity exerciseEntity) {
        return ResponseEntity.ok(exerciseService.update(exerciseEntity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.delete(id);
    }

    /**
     * Retrieves filters like BodyPart or Category for exercises.
     *
     * @return An ObjectNode containing filters for fitness exercises.
     */
    @GetMapping("/filters")
    public ObjectNode getAllFilters() {
        return exerciseService.getAllFilters();
    }
}
