package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;

@Component
public class TrainerAdapter extends AbstractAdapter<TrainerEntity, TrainerDTO> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TrainerEntity dtoToEntity(TrainerDTO dto) {
        if (dto == null) return null;
        final TrainerEntity entity = new TrainerEntity();

        entity.setTrainerID(dto.getTrainerID());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setHireDate(dto.getHireDate());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(CharBuffer.wrap(dto.getPassword())));

        return entity;
    }

    @Override
    public TrainerDTO entityToDTO(TrainerEntity entity) {
        if (entity == null) return null;
        final TrainerDTO dto = new TrainerDTO();

        dto.setTrainerID(entity.getTrainerID());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setHireDate(entity.getHireDate());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());

        return dto;
    }

}
