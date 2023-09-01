package com.mastercode.fitmaster.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The User abstract class represents a generic user in the fitness application.
 *
 * @author Uroš Gavrilović
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    /** The username used for authentication. */
    @Size(min = 3, max = 30)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    protected String username;

    /** The password used for authentication. */
    /** Not recommended to set max length because of encoder. */
    @NotEmpty
    protected String password;

    @Transient
    protected String token;
}
