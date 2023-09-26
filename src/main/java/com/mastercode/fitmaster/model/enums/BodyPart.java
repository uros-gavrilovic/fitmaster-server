package com.mastercode.fitmaster.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BodyPart implements AbstractEnum {
    CORE("CORE"), ARMS("ARMS"), BACK("BACK"), CHEST("CHEST"), SHOULDERS("SHOULDERS"), LEGS("LEGS"), FULL_BODY("FULL_BODY"), CARDIO("CARDIO"), OTHER("OTHER");

    private final String value;

}
