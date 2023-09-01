package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE"), UNKNOWN(null);

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
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Gender value: " + value));
    }

}