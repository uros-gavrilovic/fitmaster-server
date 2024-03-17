package com.mastercode.fitmaster.model;

import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseEntityTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private ExerciseEntity exerciseEntity;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.exerciseEntity = new ExerciseEntity();
        this.exerciseEntity.setExerciseID(1L);
        this.exerciseEntity.setName("testValidName");
        this.exerciseEntity.setInstructions("testValidInstructions");
        this.exerciseEntity.setBodyPart(BodyPart.OTHER);
        this.exerciseEntity.setCategory(Category.OTHER);
    }

    @Test
    public void testValidExercise() {
        Set<ConstraintViolation<ExerciseEntity>> violations = validator.validate(exerciseEntity);

        assertEquals(0, violations.size());
    }

//    @Test
//    public void testExerciseIDNotNull() {
//        exerciseEntity.setExerciseID(null); // Null exerciseEntity ID, violating @NotNull constraint
//
//        Set<ConstraintViolation<ExerciseEntity>> violations = validator.validate(exerciseEntity);
//
//        assertEquals(1, violations.size());
//        assertEquals("exerciseID", violations.iterator().next().getPropertyPath().toString());
//    }

    @Test
    public void testExerciseNameNotNull() {
        exerciseEntity.setName(null); // Null exerciseEntity name, violating @NotEmpty constraint

        Set<ConstraintViolation<ExerciseEntity>> violations = validator.validate(exerciseEntity);

        assertEquals(1, violations.size());
        assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testExerciseNameNotEmpty() {
        exerciseEntity.setName(""); // Empty exerciseEntity name, violating @NotEmpty constraint

        Set<ConstraintViolation<ExerciseEntity>> violations = validator.validate(exerciseEntity);

        assertEquals(1, violations.size());
        assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testBodyPartNotNull() {
        exerciseEntity.setBodyPart(null); // Null exerciseEntity instructions, violating @NotNull constraint

        Set<ConstraintViolation<ExerciseEntity>> violations = validator.validate(exerciseEntity);
        System.out.println(violations);

        assertEquals(1, violations.size());
        assertEquals("bodyPart", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testCategoryNotNull() {
        exerciseEntity.setCategory(null); // Null exerciseEntity instructions, violating @NotNull constraint

        Set<ConstraintViolation<ExerciseEntity>> violations = validator.validate(exerciseEntity);

        assertEquals(1, violations.size());
        assertEquals("category", violations.iterator().next().getPropertyPath().toString());
    }

}
