package com.mastercode.fitmaster.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLogger {

    // ANSI escape codes for text colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void info(String message) {
        log.info(ANSI_GREEN + "{}" + ANSI_RESET, message);
    }

    public static void warn(String message) {
        log.warn(ANSI_YELLOW + "{}" + ANSI_RESET, message);
    }

    public static void error(String message) {
        log.error(ANSI_RED + "{}" + ANSI_RESET, message);
    }
}
