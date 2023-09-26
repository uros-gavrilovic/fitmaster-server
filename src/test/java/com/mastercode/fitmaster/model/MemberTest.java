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

public class MemberTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private Member member;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.member = new Member();
        this.member.setFirstName("testValidFirstName");
        this.member.setLastName("testValidLastName");
        this.member.setUsername("testValidUsername");
        this.member.setPassword("testValidPassword");
        this.member.setPhoneNumber("+381/61-2345678");
        this.member.setBirthDate(LocalDate.now().minusYears(1));
    }

    @Test
    public void testValidMember() {
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(0, violations.size());
    }

    @Test
    public void testFirstNameNotNull() {
        member.setFirstName(null); // Null first name, violating @NotEmpty constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testFirstNameNotEmpty() {
        member.setFirstName(""); // Empty first name, violating @NotEmpty constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotNull() {
        member.setLastName(null); // Null last name, violating @NotEmpty constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotEmpty() {
        member.setLastName(""); // Empty last name, violating @NotEmpty constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameNotNull() {
        member.setUsername(null); // Null username, violating @NotEmpty constraint

        Set<ConstraintViolation<User>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyUsername() {
        member.setUsername(""); // Empty username, violating @NotEmpty and @Size constraint

        Set<ConstraintViolation<User>> violations = validator.validate(member);
        System.out.println(violations);

        assertEquals(2, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameTooLong() {
        member.setUsername("testVeryLongUserNameExceeds30Characters"); // Username too long, violating @Size constraint

        Set<ConstraintViolation<User>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidUsernamePattern() {
        member.setUsername("testInvalidUsername!/-+"); // Invalid username, violating @Pattern constraint

        Set<ConstraintViolation<User>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyPassword() {
        member.setPassword(""); // Empty password, violating @NotEmpty constraint

        Set<ConstraintViolation<User>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPasswordNotNull() {
        member.setPassword(null); // Null password, violating @NotEmpty constraint

        Set<ConstraintViolation<User>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPhoneNumberNotRequired() {
        member.setPhoneNumber(null); // Null phone number, not violating any constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidPhoneNumberPattern() {
        member.setPhoneNumber("testInvalidPhoneNumber!/-+"); // Invalid phone number, violating @NotRequiredPhoneNumber constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("phoneNumber", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testBirthDateNotRequired() {
        member.setBirthDate(null); // Null birthdate, not violating any constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        System.out.println(violations);

        assertEquals(0, violations.size());
    }

    @Test
    public void testBirthDateInFuture() {
        member.setBirthDate(LocalDate.now()
                .plusDays(1)); // Birthdate in the future, violating @NotRequiredPast constraint

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        assertEquals("birthDate", violations.iterator().next().getPropertyPath().toString());
    }
}
