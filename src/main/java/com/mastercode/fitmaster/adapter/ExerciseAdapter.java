package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseAdapter {

    public Exercise dtoToEntity(ExerciseDTO dto) {
        if (dto == null)
            return null;

        final Exercise entity = new Exercise();
        entity.setExerciseID(dto.getExerciseID());
        entity.setName(dto.getName());
        entity.setBodyPart(dto.getBodyPart());
        entity.setCategory(dto.getCategory());
        entity.setInstructions(dto.getInstructions());

        return entity;
    }

    public ExerciseDTO dtoToEntity(Exercise entity) {
        if (entity == null)
            return null;

        final ExerciseDTO dto = new ExerciseDTO();
        dto.setExerciseID(entity.getExerciseID());
        dto.setName(entity.getName());
        dto.setBodyPart(entity.getBodyPart());
        dto.setCategory(entity.getCategory());
        dto.setInstructions(entity.getInstructions());

        return dto;
    }

}
