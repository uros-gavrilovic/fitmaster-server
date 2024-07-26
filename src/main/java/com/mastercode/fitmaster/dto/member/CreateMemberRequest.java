package com.mastercode.fitmaster.dto.member;

import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.util.PatternUtils;
import com.mastercode.fitmaster.validator.annotations.Email;
import com.mastercode.fitmaster.validator.annotations.Past;
import com.mastercode.fitmaster.validator.annotations.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CreateMemberRequest (
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        Gender gender,

        String address,

        @PhoneNumber(required = false)
        String phoneNumber,

        @Past(required = false)
        LocalDate birthDate,

        @Email(required = false)
        String email,

        @Pattern(regexp = PatternUtils.USERNAME_PATTERN)
        @Size(min = PatternUtils.USERNAME_MIN_LENGTH, max = PatternUtils.USERNAME_MAX_LENGTH)
        String username,

        @Pattern(regexp = PatternUtils.PASSWORD_PATTERN)
        @Size(min = PatternUtils.PASSWORD_MIN_LENGTH, max = PatternUtils.PASSWORD_MAX_LENGTH)
        String password
) {}
