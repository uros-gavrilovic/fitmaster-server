package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum BodyPart {
    CORE("CORE"), ARMS("ARMS"), BACK("BACK"), CHEST("CHEST"), SHOULDERS("SHOULDERS"), LEGS("LEGS"), FULL_BODY("FULL_BODY"), CARDIO("CARDIO"), OTHER("OTHER");

    private final String value;

    BodyPart(String value) {
        this.value = value;
    }

    @JsonValue // Used to serialize the enum to its value
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static BodyPart fromValue(String value) {
        return Arrays.stream(BodyPart.values())
                .filter(bodyPart -> bodyPart.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid BodyPart value: " + value));
    }

}
