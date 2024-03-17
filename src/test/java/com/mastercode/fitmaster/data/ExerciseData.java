package com.mastercode.fitmaster.data;

import com.mastercode.fitmaster.model.ExerciseEntity;
import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;

public class ExerciseData {
    public static final ExerciseEntity EXERCISE_ENTITY = ExerciseEntity.builder()
            .name("Bench Press")
            .instructions("Lay on a bench and press a barbell")
            .bodyPart(BodyPart.CHEST)
            .category(Category.BARBELL)
            .build();

    public static final ExerciseEntity UPDATED_EXERCISE_ENTITY = EXERCISE_ENTITY.toBuilder()
            .name("Squat")
            .instructions("Grab a barbell and squat down")
            .bodyPart(BodyPart.LEGS)
            .build();
}
