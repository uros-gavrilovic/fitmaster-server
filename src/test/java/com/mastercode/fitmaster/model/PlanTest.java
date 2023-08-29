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

public class PlanTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    private Plan plan;
    @Mock
    private Member mockMember;
    @Mock
    private Activity mockActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.plan = new Plan();
        this.plan.setPlanID(1L);
        this.plan.setMember(mockMember);
        this.plan.setActivities(new HashSet<>(Set.of(mockActivity)));
        this.plan.setStartsAt(LocalDateTime.now().plusDays(1));
        this.plan.setEndsAt(LocalDateTime.now().plusDays(2));
    }

    @Test
    public void testValidPlan() {
        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);
        System.out.println(violations);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testPlanIDNotNull() {
        plan.setPlanID(null); // Missing planID, violating @NotNull constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("planID", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testMemberNotNull() {
        plan.setMember(null); // Missing member, violating @NotNull constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("member", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testStartsAtNotNull() {
        plan.setStartsAt(null); // Missing startsAt, violating @NotNull constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("startsAt", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testStartsAtInPast() {
        plan.setStartsAt(LocalDateTime.now().minusDays(1)); // StartsAt in the past, violating @Future constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("startsAt", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEndsAtNotNull() {
        plan.setEndsAt(null); // Missing endsAt, violating @NotNull constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("endsAt", violations.iterator().next().getPropertyPath().toString());
    }


    @Test
    public void testEndsAtInPast() {
        plan.setEndsAt(LocalDateTime.now().minusDays(1)); // EndsAt in the past, violating @Future constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("endsAt", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testActivitiesNotNull() {
        plan.setActivities(null); // Missing activities, violating @NotEmpty constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("activities", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testActivitiesNotEmpty() {
        plan.setActivities(new HashSet<>()); // Empty activities, violating @NotEmpty constraint

        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        assertEquals(1, violations.size());
        assertEquals("activities", violations.iterator().next().getPropertyPath().toString());
    }
}
