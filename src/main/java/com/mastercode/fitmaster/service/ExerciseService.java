package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Exercise;
import com.mastercode.fitmaster.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    @Autowired
    ExerciseRepository exerciseRepository;

    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }
}