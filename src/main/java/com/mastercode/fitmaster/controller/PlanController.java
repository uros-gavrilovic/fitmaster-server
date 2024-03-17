package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.exception.ValidatorException;
import com.mastercode.fitmaster.model.*;
import com.mastercode.fitmaster.service.PlanService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

/**
 * The PlanController class handles HTTP requests related to fitness plans.
 *
 * @author Uroš Gavrilović
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    /**
     * Used to validate if passed entity is valid or not.
     */
    @Autowired
    private final Validator validator;

    /**
     * Represents service class for entity PlanEntity.
     */
    @Autowired
    private PlanService planService;

    /**
     * Retrieves a list of all fitness plans.
     *
     * @return A list of PlanEntity objects representing fitness plans.
     */
    @GetMapping
    public List<PlanEntity> getAll() {
        return planService.getAll();
    }

    /**
     * Creates a new fitness plan based on the provided JSON request.
     *
     * @param jsonResponse The JSON request containing plan information.
     *
     * @return A ResponseEntity containing the created PlanEntity object and a status code of CREATED (201).
     *
     * @throws JsonProcessingException If there's an issue processing the JSON request.
     * @throws ValidatorException      If there's an issue validating the PlanEntity object.
     * @throws NullPointerException    If the provided JSON request is not valid, or a field is missing.
     */
    @PostMapping
    public ResponseEntity<PlanDTO> createPlan(@RequestBody String jsonResponse)
            throws JsonProcessingException, ValidatorException, NullPointerException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        PlanEntity planEntity = new PlanEntity();
        planEntity.setTrainerEntity(objectMapper.treeToValue(jsonNode.get("trainer"), TrainerEntity.class));
        planEntity.setMemberEntity(objectMapper.treeToValue(jsonNode.get("member"), MemberEntity.class));
        // TODO: Localization issues causes wrong time to be saved in DB.
        planEntity.setStartsAt(
                Instant.parse(jsonNode.get("startsAt").asText()).atZone(ZoneOffset.UTC).toLocalDateTime());
        planEntity.setEndsAt(Instant.parse(jsonNode.get("endsAt").asText()).atZone(ZoneOffset.UTC).toLocalDateTime());
        planEntity.setCompleted(null);

        JsonNode exercisesNode = jsonNode.get("exercises");
        for (JsonNode exerciseNode : exercisesNode) {
            try {
                ExerciseEntity exerciseEntity = objectMapper.treeToValue(exerciseNode, ExerciseEntity.class);

                ActivityEntity activityEntity = new ActivityEntity();
                activityEntity.setPlanEntity(planEntity);
                activityEntity.setExerciseEntity(exerciseEntity);
                activityEntity.setReps(exerciseNode.get("reps").asInt());
                activityEntity.setSets(exerciseNode.get("sets").asInt());

                planEntity.getActivities().add(activityEntity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);
        if (!violations.isEmpty()) {
            throw new ValidatorException(violations.toString());
        }

        PlanDTO createdPlan = planService.create(planEntity);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @PostMapping("/created-by-member")
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanEntity planEntity) {
        planEntity.getActivities().forEach(activity -> activity.setPlanEntity(planEntity));
        planEntity.setTrainerEntity(null);

        return new ResponseEntity<>(planService.create(planEntity), HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of fitness plans by trainer ID.
     *
     * @param id The unique ID of the trainer.
     *
     * @return A ResponseEntity containing a set of PlanEntity objects associated with the trainer
     * and a status code of OK (200).
     */
    @GetMapping("/trainer/{id}")
    public ResponseEntity<Set<PlanEntity>> getAllByTrainer(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findByTrainerId(id), HttpStatus.OK);
    }

    /**
     * Retrieves a list of fitness plans by member ID.
     *
     * @param id The unique ID of the member.
     *
     * @return A ResponseEntity containing a set of PlanEntity objects associated with the member
     * and a status code of OK (200).
     */
    @GetMapping("/member/{id}")
    public ResponseEntity<Set<PlanEntity>> getAllByMember(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findByMemberId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanEntity> getById(@PathVariable Long id) {
        PlanEntity planEntity = planService.findByID(id);
        return new ResponseEntity<>(planEntity, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PlanDTO> updatePlan(@Valid @RequestBody PlanEntity planEntity) {
        planEntity.getActivities().forEach(activity -> activity.setPlanEntity(planEntity));
        PlanDTO updatedPlan = planService.update(planEntity);

        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @PostMapping("/remove-trainer/{id}")
    public ResponseEntity<PlanDTO> removeTrainer(@NotEmpty @PathVariable Long id) {
        PlanEntity planEntity = planService.findByID(id);
        planEntity.setTrainerEntity(null);

        return new ResponseEntity<>(planService.update(planEntity), HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<PlanDTO> cancelPlan(@NotEmpty @PathVariable Long id) {
        PlanEntity planEntity = planService.findByID(id);
        planEntity.setCompleted(false);

        return new ResponseEntity<>(planService.update(planEntity), HttpStatus.OK);
    }

    /**
     * Deletes a fitness plan by its unique ID.
     *
     * @param id The unique ID of the plan to delete.
     *
     * @return A ResponseEntity with a status code of NO_CONTENT (204) if the plan was successfully deleted.
     *
     * @throws MethodArgumentNotValidException If the provided ID is empty or null.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PlanEntity> deletePlan(@NotEmpty @PathVariable Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
