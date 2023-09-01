package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Retrieves a list of ExerciseDTO objects containing summarized information about exercises.
     *
     * @return A list of ExerciseDTO objects.
     */
    @GetMapping("/dto")
    public List<ExerciseDTO> getAllDTOs() {
        return exerciseService.getAllDTOs();
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
