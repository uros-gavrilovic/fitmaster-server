package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mastercode.fitmaster.model.enums.Role;
import com.mastercode.fitmaster.util.PatternUtils;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({@JsonSubTypes.Type(value = MemberEntity.class, name = "MEMBER"), @JsonSubTypes.Type(value = TrainerEntity.class, name = "TRAINER")})
public abstract class UserEntity {

    @NotNull
    @Pattern(regexp = PatternUtils.USERNAME_PATTERN)
    @Size(min = PatternUtils.USERNAME_MIN_LENGTH, max = PatternUtils.USERNAME_MAX_LENGTH)
    protected String username;

    @NotNull
    @Pattern(regexp = PatternUtils.PASSWORD_PATTERN)
    @Size(min = PatternUtils.PASSWORD_MIN_LENGTH, max = PatternUtils.PASSWORD_MAX_LENGTH)
    protected String password;

    protected String email;

    protected boolean emailVerified;

    @Transient
    protected String verificationToken;

    protected File avatar;

    @Transient
    protected String token;

    @Transient
    protected Role role;

}
