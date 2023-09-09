package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mastercode.fitmaster.model.enums.Role;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({@JsonSubTypes.Type(value = Member.class, name = "MEMBER"), @JsonSubTypes.Type(value = Trainer.class, name = "TRAINER")})
public abstract class User {

    protected String username;

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
