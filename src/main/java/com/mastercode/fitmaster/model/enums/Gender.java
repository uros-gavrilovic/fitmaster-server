package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing gender options.
 *
 * @author Uroš Gavrilović
 */
public enum Gender {
    /**
     * Male gender.
     */
    MALE("MALE"),

    /**
     * Female gender.
     */
    FEMALE("FEMALE"),

    /**
     * Unknown or unspecified gender.
     */
    UNKNOWN(null);

    private final String value;

    /**
     * Constructor for the Gender enum.
     *
     * @param value The string representation of the gender.
     */
    Gender(String value) {
        this.value = value;
    }

    /**
     * Get the string value of the enum.
     *
     * @return The string representation of the enum.
     */
    @JsonValue // Used to serialize the enum to its value
    public String getValue() {
        return value;
    }

    /**
     * Create a Gender enum from its string representation.
     *
     * @param value The string representation of the gender.
     *
     * @return The corresponding Gender enum value.
     *
     * @throws IllegalArgumentException If an invalid value is provided.
     */
    @JsonCreator
    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equals(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid Gender value: " + value);
    }
}
