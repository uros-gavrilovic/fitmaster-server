package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Uroš Gavrilović
 * Enum representing different body parts targeted by the exercises.
 */
public enum BodyPart {
    /**
     * Core exercises category.
     */
    CORE("CORE"),

    /**
     * Arm exercises category.
     */
    ARMS("ARMS"),

    /**
     * Back exercises category.
     */
    BACK("BACK"),

    /**
     * Chest exercises category.
     */
    CHEST("CHEST"),

    /**
     * Shoulder exercises category.
     */
    SHOULDERS("SHOULDERS"),

    /**
     * Leg exercises category.
     */
    LEGS("LEGS"),

    /**
     * Full body exercises category.
     */
    FULL_BODY("FULL_BODY"),

    /**
     * Cardiovascular exercises category.
     */
    CARDIO("CARDIO"),

    /**
     * Other or unspecified category.
     */
    OTHER("OTHER");
    /**
     * The string representation of the body part.
     */
    private final String value;

    /**
     * Constructor for the BodyPart enum.
     *
     * @param value The string representation of the body part.
     */
    BodyPart(String value) {
        this.value = value;
    }

    /**
     * Get the string value of the enum. Serialized enum to its value.
     *
     * @return The string representation of the enum.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Create a BodyPart enum from its string representation.
     *
     * @param value The string representation of the body part.
     *
     * @return The corresponding BodyPart enum value.
     *
     * @throws IllegalArgumentException If an invalid value is provided.
     */
    @JsonCreator
    public static BodyPart fromValue(String value) {
        for (BodyPart bodyPart : BodyPart.values()) {
            if (bodyPart.value.equals(value)) {
                return bodyPart;
            }
        }
        throw new IllegalArgumentException("Invalid BodyPart value: " + value);
    }
}
