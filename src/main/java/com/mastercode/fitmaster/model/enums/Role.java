package com.mastercode.fitmaster.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements AbstractEnum {
    TRAINER("TRAINER"), MEMBER("MEMBER");

    private final String value;
}
