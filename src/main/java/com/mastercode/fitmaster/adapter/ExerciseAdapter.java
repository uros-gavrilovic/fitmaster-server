package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseAdapter extends AbstractAdapter<ExerciseEntity, ExerciseDTO> {

    @Override
    public ExerciseEntity dtoToEntity(ExerciseDTO dto) {
        if (dto == null) return null;
        final ExerciseEntity entity = new ExerciseEntity();

        entity.setExerciseID(dto.getExerciseID());
        entity.setName(dto.getName());
        entity.setBodyPart(dto.getBodyPart());
        entity.setCategory(dto.getCategory());
        entity.setInstructions(dto.getInstructions());

        return entity;
    }

    @Override
    public ExerciseDTO entityToDTO(ExerciseEntity entity) {
        if (entity == null) return null;
        final ExerciseDTO dto = new ExerciseDTO();

        dto.setExerciseID(entity.getExerciseID());
        dto.setName(entity.getName());
        dto.setBodyPart(entity.getBodyPart());
        dto.setCategory(entity.getCategory());
        dto.setInstructions(entity.getInstructions());

        return dto;
    }

}
