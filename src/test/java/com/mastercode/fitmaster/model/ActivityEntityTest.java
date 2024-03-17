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

public class ActivityEntityTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    private ActivityEntity activityEntity;
    @Mock
    private PlanEntity mockPlanEntity;
    @Mock
    private ExerciseEntity mockExerciseEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.activityEntity = new ActivityEntity();
        activityEntity.setActivityID(1L);
        activityEntity.setPlanEntity(mockPlanEntity);
        activityEntity.setExerciseEntity(mockExerciseEntity);
        activityEntity.setSets(1);
        activityEntity.setReps(1);
        activityEntity.setComment("testComment");
    }

    @Test
    public void testValidActivity() {
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(0, violations.size());
    }

//    @Test
//    public void testActivityIDNotNull() {
//        activityEntity.setActivityID(null);
//        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);
//
//        assertEquals(1, violations.size());
//    }

    @Test
    public void testPlanNotNull() {
        activityEntity.setPlanEntity(null);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testExerciseNotNull() {
        activityEntity.setExerciseEntity(null);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testSetsNotNull() {
        activityEntity.setSets(null);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testSetsPositive() {
        activityEntity.setSets(-1);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testRepsNotNull() {
        activityEntity.setReps(null);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testRepsPositive() {
        activityEntity.setReps(-1);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(1, violations.size());
    }

    @Test
    public void testCommentNotRequired() {
        activityEntity.setComment(null);
        Set<ConstraintViolation<ActivityEntity>> violations = validator.validate(activityEntity);

        assertEquals(0, violations.size());
    }
}
