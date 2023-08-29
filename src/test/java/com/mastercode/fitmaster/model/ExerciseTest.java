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

public class ExerciseTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private Exercise exercise;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.exercise = new Exercise();
        this.exercise.setExerciseID(1L);
        this.exercise.setName("testValidName");
        this.exercise.setInstructions("testValidInstructions");
        this.exercise.setBodyPart(BodyPart.OTHER);
        this.exercise.setCategory(Category.OTHER);
    }

    @Test
    public void testValidExercise() {
        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        assertEquals(0, violations.size());
    }

    @Test
    public void testExerciseIDNotNull() {
        exercise.setExerciseID(null); // Null exercise ID, violating @NotNull constraint

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        assertEquals(1, violations.size());
        assertEquals("exerciseID", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testExerciseNameNotNull() {
        exercise.setName(null); // Null exercise name, violating @NotEmpty constraint

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        assertEquals(1, violations.size());
        assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testExerciseNameNotEmpty() {
        exercise.setName(""); // Empty exercise name, violating @NotEmpty constraint

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        assertEquals(1, violations.size());
        assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testBodyPartNotNull() {
        exercise.setBodyPart(null); // Null exercise instructions, violating @NotNull constraint

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);
        System.out.println(violations);

        assertEquals(1, violations.size());
        assertEquals("bodyPart", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testCategoryNotNull() {
        exercise.setCategory(null); // Null exercise instructions, violating @NotNull constraint

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        assertEquals(1, violations.size());
        assertEquals("category", violations.iterator().next().getPropertyPath().toString());
    }

}
