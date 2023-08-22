package com.mastercode.fitmaster.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class DescriptionUtils {
    public static String errorDescriptionPath = "desc/" + getAppName() + "/error_description";
    public static String actionDescriptionPath = "desc/" + getAppName() + "/action_description";

    public static String getActionDescriptionPath(String key) {
        Locale locale = new Locale(getAppLocale());
        ResourceBundle errorResourceBundle = ResourceBundle.getBundle(errorDescriptionPath, locale);

        String errorMessage;
        try {
            errorMessage = errorResourceBundle.getString(key);
        } catch (Exception e) {
            errorMessage = "An error occurred.";
        }
        return errorMessage;
    }

    public static String getErrorDescription(String key) {
        Locale locale = new Locale(getAppLocale());
        ResourceBundle errorResourceBundle = ResourceBundle.getBundle(errorDescriptionPath, locale);

        String errorMessage;
        try {
            errorMessage = errorResourceBundle.getString(key);
        } catch (Exception e) {
            errorMessage = "An error occurred.";
        }
        return errorMessage;
    }

    public static String getAppName() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName;

        try {
            appName = bundle.getString("app.name");
        } catch (Exception e) {
            appName = "fitmaster";
        }

        return appName;
    }

    public static String getAppLocale() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String defaultLocale;

        try {
            defaultLocale = bundle.getString("app.locale");
        } catch (Exception e) {
            defaultLocale = "en";
        }

        return defaultLocale;
    }

    public static String getAppVersion() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appVersion;

        try {
            appVersion = bundle.getString("app.version");
        } catch (Exception e) {
            appVersion = "0.1";
        }

        return appVersion;
    }
}
