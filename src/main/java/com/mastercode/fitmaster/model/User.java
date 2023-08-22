package com.mastercode.fitmaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The User abstract class represents a generic user in the fitness application.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    /** The username used for authentication. */
    protected String username;

    /** The password used for authentication. */
    protected String password;
}
