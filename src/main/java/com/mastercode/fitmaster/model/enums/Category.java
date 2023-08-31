package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Category {
    BARBELL("BARBELL"), DUMBBELL("DUMBBELL"), MACHINE("MACHINE"), BODY_WEIGHT("BODY_WEIGHT"), CARDIO("CARDIO"), DURATION("DURATION"), OTHER("OTHER");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Category fromValue(String value) {
        return Arrays.stream(Category.values())
                .filter(category -> category.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Category value: " + value));
    }

}
