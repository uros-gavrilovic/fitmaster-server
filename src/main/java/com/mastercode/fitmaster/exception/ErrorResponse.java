package com.mastercode.fitmaster.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorResponse {
    private final String title;
    private final String message;
    private final String timestamp;

    public ErrorResponse(String title, String message) {
        this.title = title;
        this.message = message;
        this.timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
