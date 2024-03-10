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

public class MemberEntityTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private MemberEntity memberEntity;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        this.memberEntity = new MemberEntity();
        this.memberEntity.setFirstName("testValidFirstName");
        this.memberEntity.setLastName("testValidLastName");
        this.memberEntity.setUsername("testValidUsername");
        this.memberEntity.setPassword("testValidPassword");
        this.memberEntity.setPhoneNumber("+381/61-2345678");
        this.memberEntity.setBirthDate(LocalDate.now().minusYears(1));
    }

    @Test
    public void testValidMember() {
        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(0, violations.size());
    }

    @Test
    public void testFirstNameNotNull() {
        memberEntity.setFirstName(null); // Null first name, violating @NotEmpty constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testFirstNameNotEmpty() {
        memberEntity.setFirstName(""); // Empty first name, violating @NotEmpty constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotNull() {
        memberEntity.setLastName(null); // Null last name, violating @NotEmpty constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testLastNameNotEmpty() {
        memberEntity.setLastName(""); // Empty last name, violating @NotEmpty constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameNotNull() {
        memberEntity.setUsername(null); // Null username, violating @NotEmpty constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyUsername() {
        memberEntity.setUsername(""); // Empty username, violating @NotEmpty and @Size constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(memberEntity);
        System.out.println(violations);

        assertEquals(2, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testUsernameTooLong() {
        memberEntity.setUsername(
                "testVeryLongUserNameExceeds30Characters"); // Username too long, violating @Size constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidUsernamePattern() {
        memberEntity.setUsername("testInvalidUsername!/-+"); // Invalid username, violating @Pattern constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testEmptyPassword() {
        memberEntity.setPassword(""); // Empty password, violating @NotEmpty constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPasswordNotNull() {
        memberEntity.setPassword(null); // Null password, violating @NotEmpty constraint

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testPhoneNumberNotRequired() {
        memberEntity.setPhoneNumber(null); // Null phone number, not violating any constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidPhoneNumberPattern() {
        memberEntity.setPhoneNumber(
                "testInvalidPhoneNumber!/-+"); // Invalid phone number, violating @NotRequiredPhoneNumber constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("phoneNumber", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testBirthDateNotRequired() {
        memberEntity.setBirthDate(null); // Null birthdate, not violating any constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);
        System.out.println(violations);

        assertEquals(0, violations.size());
    }

    @Test
    public void testBirthDateInFuture() {
        memberEntity.setBirthDate(
                LocalDate.now().plusDays(1)); // Birthdate in the future, violating @NotRequiredPast constraint

        Set<ConstraintViolation<MemberEntity>> violations = validator.validate(memberEntity);

        assertEquals(1, violations.size());
        assertEquals("birthDate", violations.iterator().next().getPropertyPath().toString());
    }
}
