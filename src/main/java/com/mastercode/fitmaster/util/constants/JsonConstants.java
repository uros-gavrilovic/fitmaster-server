package com.mastercode.fitmaster.util.constants;

public class JsonConstants {
    private static final String BASE_PATH = "$.";

    public static String path(String field) {
        return BASE_PATH + field;
    }

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String STATUS = "status";
}
