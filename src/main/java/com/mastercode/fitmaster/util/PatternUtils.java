package com.mastercode.fitmaster.util;

public class PatternUtils {
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]*$";
    public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9._-]*$";
    public static final String PHONE_NUMBER_PATTERN = "\\+\\(\\d{3}\\) \\d{2}-\\d{3}-\\d{4}";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 64;

    public static final int PASSWORD_MIN_LENGTH = 3;
    public static final int PASSWORD_MAX_LENGTH = 255;
}
