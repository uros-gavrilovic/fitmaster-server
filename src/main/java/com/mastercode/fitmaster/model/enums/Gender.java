package com.mastercode.fitmaster.model.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN(null);

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue // Used to serialize the enum to its value
    public String getValue() {
        return value;
    }

    // Custom constructor to handle parsing of the enum from JSON
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