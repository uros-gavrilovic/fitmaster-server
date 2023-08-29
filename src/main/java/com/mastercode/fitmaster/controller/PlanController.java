package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mastercode.fitmaster.exception.ValidatorException;
import com.mastercode.fitmaster.model.*;
import com.mastercode.fitmaster.service.PlanService;
import jakarta.validation.ConstraintViolation;
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
 * @author Uroš Gavrilović
 * The PlanController class handles HTTP requests related to fitness plans.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    @Autowired
    private final Validator validator;

    @Autowired
    private PlanService planService;

    /**
     * Retrieves a list of all fitness plans.
     *
     * @return A list of Plan objects representing fitness plans.
     */
    @GetMapping
    public List<Plan> getAll() {
        return planService.getAll();
    }

    /**
     * Creates a new fitness plan based on the provided JSON request.
     *
     * @param jsonResponse The JSON request containing plan information.
     *
     * @return A ResponseEntity containing the created Plan object and a status code of CREATED (201).
     *
     * @throws JsonProcessingException If there's an issue processing the JSON request.
     * @throws ValidatorException      If there's an issue validating the Plan object.
     * @throws NullPointerException    If the provided JSON request is not valid, or a field is missing.
     */
    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody String jsonResponse) throws JsonProcessingException, ValidatorException, NullPointerException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        Plan plan = new Plan();
        plan.setTrainer(objectMapper.treeToValue(jsonNode.get("trainer"), Trainer.class));
        plan.setMember(objectMapper.treeToValue(jsonNode.get("member"), Member.class));
        // TODO: Localization issues causes wrong time to be saved in DB.
        plan.setStartsAt(Instant.parse(jsonNode.get("startsAt").asText()).atZone(ZoneOffset.UTC).toLocalDateTime());
        plan.setEndsAt(Instant.parse(jsonNode.get("endsAt").asText()).atZone(ZoneOffset.UTC).toLocalDateTime());

        JsonNode exercisesNode = jsonNode.get("exercises");
        for (JsonNode exerciseNode : exercisesNode) {
            try {
                Exercise exercise = objectMapper.treeToValue(exerciseNode, Exercise.class);

                Activity activity = new Activity();
                activity.setPlan(plan);
                activity.setExercise(exercise);
                activity.setReps(exerciseNode.get("reps").asInt());
                activity.setSets(exerciseNode.get("sets").asInt());

                plan.getActivities().add(activity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);
        if (!violations.isEmpty()) {
            throw new ValidatorException(violations.toString());
        }

        Plan createdPlan = planService.create(plan);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of fitness plans by trainer ID.
     *
     * @param id The unique ID of the trainer.
     *
     * @return A ResponseEntity containing a set of Plan objects associated with the trainer
     * and a status code of OK (200).
     */
    @GetMapping("/trainer/{id}")
    public ResponseEntity<Set<Plan>> getAllByTrainer(@PathVariable Long id) {
        Set<Plan> plans = planService.findByTrainerId(id);
        return new ResponseEntity<>(plans, HttpStatus.OK);
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
    public ResponseEntity<Plan> deletePlan(@NotEmpty @PathVariable Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
