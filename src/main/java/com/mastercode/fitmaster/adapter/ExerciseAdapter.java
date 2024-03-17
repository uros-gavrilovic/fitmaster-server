package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseAdapter extends AbstractAdapter<ExerciseEntity, ExerciseDTO> {
    @Override
    public ExerciseDTO entityToDTO(ExerciseEntity entity) {
        if (entity == null) return null;

        return ExerciseDTO.builder()
                .name(entity.getName())
                .bodyPart(entity.getBodyPart())
                .category(entity.getCategory())
                .instructions(entity.getInstructions())
                .build();
    }
}
