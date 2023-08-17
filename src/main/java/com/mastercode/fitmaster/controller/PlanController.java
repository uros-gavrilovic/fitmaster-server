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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        plan.setDateTime(LocalDateTime.parse(jsonNode.get("dateTime").asText(), formatter));
        plan.setComment(jsonNode.get("comment").asText());

//        int index = 0;
        int[] index = {0}; // Wrap index in an array to make it effectively final
        jsonNode.get("exercises").forEach((exerciseObject) -> {
                    try {
                        Activity activity = new Activity();

                        activity.setPlan(plan);
                        activity.setExercise(objectMapper.treeToValue(jsonNode.get("exercises"), Exercise[].class)[index[0]++]);
                        activity.setReps(exerciseObject.get("reps").asInt());
                        activity.setSets(exerciseObject.get("sets").asInt());

                        plan.getActivities().add(activity);

// ---------------------------------

//                        Activity activity = new Activity();
//
//                        activity.setPlan(plan);
//
//                        // Convert exerciseNode to Exercise object
//                        Exercise exercise = objectMapper.treeToValue(exerciseNode, Exercise.class);
//                        activity.setExercise(exercise);
//
//                        activity.setReps(exerciseNode.get("reps").asInt());
//                        activity.setSets(exerciseNode.get("sets").asInt());
//
//                        plan.getActivities().add(activity);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        Plan createdPlan = planService.create(plan);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }
}