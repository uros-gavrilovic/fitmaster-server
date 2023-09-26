package com.mastercode.fitmaster.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender implements AbstractEnum {
    MALE("MALE"), FEMALE("FEMALE"), UNKNOWN(null);

    private final String value;
}