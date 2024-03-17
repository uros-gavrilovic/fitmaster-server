package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;

@Component
public class TrainerAdapter extends AbstractAdapter<TrainerEntity, TrainerDTO> {
    @Override
    public TrainerDTO entityToDTO(TrainerEntity entity) {
        if (entity == null) return null;
        
        return TrainerDTO.builder()
                .trainerID(entity.getTrainerID())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .gender(entity.getGender())
                .address(entity.getAddress())
                .hireDate(entity.getHireDate())
                .build();
    }

}
