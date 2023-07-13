package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.ExerciseAdapter;
import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.Exercise;
import com.mastercode.fitmaster.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements AbstractService<Exercise, ExerciseDTO> {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseAdapter exerciseAdapter;

    @Override
    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findByID(Long id) {
        return exerciseRepository.getByExerciseID(id);
    }

    @Override
    public List<ExerciseDTO> getAllDTOs() {
        return exerciseAdapter.entitiesToDTOs(e)
    }
}