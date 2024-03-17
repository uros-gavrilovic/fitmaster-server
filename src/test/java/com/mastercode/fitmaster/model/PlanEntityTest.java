package com.mastercode.fitmaster.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlanEntityTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    private PlanEntity planEntity;
    @Mock
    private MemberEntity mockMemberEntity;
    @Mock
    private ActivityEntity mockActivityEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.planEntity = new PlanEntity();
        this.planEntity.setPlanID(1L);
        this.planEntity.setMemberEntity(mockMemberEntity);
        this.planEntity.setActivities(new HashSet<>(Set.of(mockActivityEntity)));
        this.planEntity.setStartsAt(LocalDateTime.now().plusDays(1));
        this.planEntity.setEndsAt(LocalDateTime.now().plusDays(2));
    }

    @Test
    public void testValidPlan() {
        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);
        System.out.println(violations);

        assertTrue(violations.isEmpty());
    }

//    @Test
//    public void testPlanIDNotNull() {
//        planEntity.setPlanID(null); // Missing planID, violating @NotNull constraint
//
//        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);
//
//        assertEquals(1, violations.size());
//        assertEquals("planID", violations.iterator().next().getPropertyPath().toString());
//    }

    @Test
    public void testMemberNotNull() {
        planEntity.setMemberEntity(null); // Missing member, violating @NotNull constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("memberEntity", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testStartsAtNotNull() {
        planEntity.setStartsAt(null); // Missing startsAt, violating @NotNull constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("startsAt", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testStartsAtInPast() {
        planEntity.setStartsAt(LocalDateTime.now().minusDays(1)); // StartsAt in the past, violating @Future constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("startsAt", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEndsAtNotNull() {
        planEntity.setEndsAt(null); // Missing endsAt, violating @NotNull constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("endsAt", violations.iterator().next().getPropertyPath().toString());
    }


    @Test
    public void testEndsAtInPast() {
        planEntity.setEndsAt(LocalDateTime.now().minusDays(1)); // EndsAt in the past, violating @Future constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("endsAt", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testActivitiesNotNull() {
        planEntity.setActivities(null); // Missing activities, violating @NotEmpty constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("activities", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testActivitiesNotEmpty() {
        planEntity.setActivities(new HashSet<>()); // Empty activities, violating @NotEmpty constraint

        Set<ConstraintViolation<PlanEntity>> violations = validator.validate(planEntity);

        assertEquals(1, violations.size());
        assertEquals("activities", violations.iterator().next().getPropertyPath().toString());
    }
}
