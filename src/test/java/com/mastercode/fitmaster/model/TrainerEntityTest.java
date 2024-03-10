package com.mastercode.fitmaster.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainerEntityTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private TrainerEntity trainerEntity;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.trainerEntity = new TrainerEntity();
        this.trainerEntity.setFirstName("testValidFirstName");
        this.trainerEntity.setLastName("testValidLastName");
        this.trainerEntity.setUsername("testValidUsername");
        this.trainerEntity.setPassword("testValidPassword");
        this.trainerEntity.setPhoneNumber("+381/61-2345678");
        this.trainerEntity.setHireDate(LocalDate.now().minusDays(1));
    }

    @Test
    public void testValidTrainer() {
        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testFirstNameNotNull() {
        trainerEntity.setFirstName(null); // Null first name, violating @NotEmpty constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testFirstNameNotEmpty() {
        trainerEntity.setFirstName(""); // Empty first name, violating @NotEmpty constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotNull() {
        trainerEntity.setLastName(null); // Null last name, violating @NotEmpty constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotEmpty() {
        trainerEntity.setLastName(""); // Empty last name, violating @NotEmpty constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameNotNull() {
        trainerEntity.setUsername(null); // Null username, violating @NotEmpty constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyUsername() {
        trainerEntity.setUsername(""); // Empty username, violating @NotEmpty and @Size constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(trainerEntity);

        assertEquals(2, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameTooLong() {
        trainerEntity.setUsername(
                "testVeryLongUserNameExceeds30Characters"); // Username too long, violating @Size constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidUsernamePattern() {
        trainerEntity.setUsername("testInvalidUsername!/-+"); // Invalid username, violating @Pattern constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPasswordNotNull() {
        trainerEntity.setPassword(null); // Null password, violating @NotEmpty constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyPassword() {
        trainerEntity.setPassword(""); // Empty password, violating @NotEmpty constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPhoneNumberNotRequired() {
        trainerEntity.setPhoneNumber(null); // Null phone number, not violating @IfExistsThenPhoneNumber constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidPhoneNumberPattern() {
        trainerEntity.setPhoneNumber(
                "testInvalidPhoneNumber!/-+"); // Invalid phone number, violating @IfExistsThenPhoneNumber constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("phoneNumber", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testFutureHireDate() {
        trainerEntity.setHireDate(LocalDate.now().plusDays(1)); // Future hire date, violating @Past constraint

        Set<ConstraintViolation<TrainerEntity>> violations = validator.validate(trainerEntity);

        assertEquals(1, violations.size());
        assertEquals("hireDate", violations.iterator().next().getPropertyPath().toString());
    }
}

