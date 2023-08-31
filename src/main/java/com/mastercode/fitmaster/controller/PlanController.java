package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mastercode.fitmaster.model.*;
import com.mastercode.fitmaster.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> getAll() {
        return planService.getAll();
    }

    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody String jsonResponse) throws JsonProcessingException {
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

        Plan createdPlan = planService.create(plan);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlan(@PathVariable Long id) {
        Plan plan = planService.findByID(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @GetMapping("/trainer/{id}")
    public ResponseEntity<Set<Plan>> getAllByTrainer(@PathVariable Long id) {
        Set<Plan> plans = planService.findByTrainerId(id);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Set<Plan>> getAllByMember(@PathVariable Long id) {
        Set<Plan> plans = planService.findByMemberId(id);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plan> deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
