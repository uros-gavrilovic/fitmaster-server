package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Uroš Gavrilović
 * Enum representing different categories of exercise.
 */
public enum Category {
    /**
     * Barbell exercises category.
     */
    BARBELL("BARBELL"),

    /**
     * Dumbbell exercises category.
     */
    DUMBBELL("DUMBBELL"),

    /**
     * Machine-based exercises category.
     */
    MACHINE("MACHINE"),

    /**
     * Body weight exercises category.
     */
    BODY_WEIGHT("BODY_WEIGHT"),

    /**
     * Cardiovascular exercises category.
     */
    CARDIO("CARDIO"),

    /**
     * Exercises based on duration category.
     */
    DURATION("DURATION"),

    /**
     * Other or unspecified category.
     */
    OTHER("OTHER");

    private final String value;

    /**
     * Constructor for the Category enum.
     *
     * @param value The string representation of the category.
     */
    Category(String value) {
        this.value = value;
    }

    /**
     * Get the string value of the enum.
     *
     * @return The string representation of the enum.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Create a Category enum from its string representation.
     *
     * @param value The string representation of the category.
     *
     * @return The corresponding Category enum value.
     *
     * @throws IllegalArgumentException If an invalid value is provided.
     */
    @JsonCreator
    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.value.equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid Category value: " + value);
    }
}
