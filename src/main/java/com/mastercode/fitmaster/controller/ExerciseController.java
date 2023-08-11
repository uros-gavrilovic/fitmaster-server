package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.Exercise;
import com.mastercode.fitmaster.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/dto")
    public List<ExerciseDTO> getAllDTOs() {
        return exerciseService.getAllDTOs();
    }
}
