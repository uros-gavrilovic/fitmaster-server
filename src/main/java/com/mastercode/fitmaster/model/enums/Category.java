package com.mastercode.fitmaster.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category implements AbstractEnum {
    BARBELL("BARBELL"), DUMBBELL("DUMBBELL"), MACHINE("MACHINE"), BODY_WEIGHT("BODY_WEIGHT"), CARDIO("CARDIO"), DURATION("DURATION"), OTHER("OTHER");

    private final String value;
}
