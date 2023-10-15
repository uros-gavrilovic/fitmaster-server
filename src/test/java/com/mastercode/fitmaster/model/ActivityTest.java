package com.mastercode.fitmaster.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    private Activity activity;
    @Mock
    private Plan mockPlan;
    @Mock
    private Exercise mockExercise;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.activity = new Activity();
        activity.setActivityID(1L);
        activity.setPlan(mockPlan);
        activity.setExercise(mockExercise);
        activity.setSets(1);
        activity.setReps(1);
        activity.setComment("testComment");
    }

    @Test
    public void testValidActivity() {
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(0, violations.size());
    }

    @Test
    public void testActivityIDNotNull() {
        activity.setActivityID(null);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testPlanNotNull() {
        activity.setPlan(null);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testExerciseNotNull() {
        activity.setExercise(null);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testSetsNotNull() {
        activity.setSets(null);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testSetsPositive() {
        activity.setSets(-1);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testRepsNotNull() {
        activity.setReps(null);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testRepsPositive() {
        activity.setReps(-1);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testCommentNotRequired() {
        activity.setComment(null);
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);

        assertEquals(0, violations.size());
    }
}
