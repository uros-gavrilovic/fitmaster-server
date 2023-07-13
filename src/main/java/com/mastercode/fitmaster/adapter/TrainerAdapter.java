package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerAdapter implements AbstractAdapter<Trainer, TrainerDTO> {

    @Override
    public Trainer dtoToEntity(final TrainerDTO dto) {
        if (dto == null) return null;
        final Trainer entity = new Trainer();

        entity.setTrainerID(dto.getTrainerID());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setHireDate(dto.getHireDate());

        return entity;
    }

    @Override
    public TrainerDTO entityToDTO(final Trainer entity) {
        if (entity == null) return null;
        final TrainerDTO dto = new TrainerDTO();

        dto.setTrainerID(entity.getTrainerID());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setGender(entity.getGender());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setHireDate(entity.getHireDate());

        return dto;
    }

}
