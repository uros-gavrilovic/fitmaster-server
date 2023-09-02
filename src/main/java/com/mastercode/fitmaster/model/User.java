package com.mastercode.fitmaster.model;

import com.mastercode.fitmaster.model.enums.Role;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    protected String username;

    protected String password;

    protected String email;

    @Transient
    protected String token;

    @Transient
    protected Role role;

}
