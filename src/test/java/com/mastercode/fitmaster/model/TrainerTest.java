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

public class TrainerTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private Trainer trainer;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.trainer = new Trainer();
        this.trainer.setFirstName("testValidFirstName");
        this.trainer.setLastName("testValidLastName");
        this.trainer.setUsername("testValidUsername");
        this.trainer.setPassword("testValidPassword");
        this.trainer.setPhoneNumber("+381/61-2345678");
        this.trainer.setHireDate(LocalDate.now().minusDays(1));
    }

    @Test
    public void testValidTrainer() {
        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testFirstNameNotNull() {
        trainer.setFirstName(null); // Null first name, violating @NotEmpty constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testFirstNameNotEmpty() {
        trainer.setFirstName(""); // Empty first name, violating @NotEmpty constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotNull() {
        trainer.setLastName(null); // Null last name, violating @NotEmpty constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotEmpty() {
        trainer.setLastName(""); // Empty last name, violating @NotEmpty constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameNotNull() {
        trainer.setUsername(null); // Null username, violating @NotEmpty constraint

        Set<ConstraintViolation<User>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyUsername() {
        trainer.setUsername(""); // Empty username, violating @NotEmpty and @Size constraint

        Set<ConstraintViolation<User>> violations = validator.validate(trainer);

        assertEquals(2, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameTooLong() {
        trainer.setUsername("testVeryLongUserNameExceeds30Characters"); // Username too long, violating @Size constraint

        Set<ConstraintViolation<User>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidUsernamePattern() {
        trainer.setUsername("testInvalidUsername!/-+"); // Invalid username, violating @Pattern constraint

        Set<ConstraintViolation<User>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPasswordNotNull() {
        trainer.setPassword(null); // Null password, violating @NotEmpty constraint

        Set<ConstraintViolation<User>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyPassword() {
        trainer.setPassword(""); // Empty password, violating @NotEmpty constraint

        Set<ConstraintViolation<User>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPhoneNumberNotRequired() {
        trainer.setPhoneNumber(null); // Null phone number, not violating @IfExistsThenPhoneNumber constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidPhoneNumberPattern() {
        trainer.setPhoneNumber("testInvalidPhoneNumber!/-+"); // Invalid phone number, violating @IfExistsThenPhoneNumber constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("phoneNumber", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testFutureHireDate() {
        trainer.setHireDate(LocalDate.now().plusDays(1)); // Future hire date, violating @Past constraint

        Set<ConstraintViolation<Trainer>> violations = validator.validate(trainer);

        assertEquals(1, violations.size());
        assertEquals("hireDate", violations.iterator().next().getPropertyPath().toString());
    }
}

