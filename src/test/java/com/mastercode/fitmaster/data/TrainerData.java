package com.mastercode.fitmaster.data;

import com.mastercode.fitmaster.model.TrainerEntity;

import java.time.LocalDate;

public class TrainerData {
    public static final TrainerEntity TRAINER_ENTITY = TrainerEntity.builder()
            .trainerID(0L)
            .firstName("John")
            .lastName("Doe")
            .phoneNumber("+(123) 45-678-9012")
            .address("123 Main St.")
            .hireDate(LocalDate.now().minusYears(1))
            .username("john_doe")
            .password("password")
            .build();
}
