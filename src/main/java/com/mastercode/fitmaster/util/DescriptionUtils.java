package com.mastercode.fitmaster.util;

import com.mastercode.fitmaster.util.constants.ApplicationConstants;
import com.mastercode.fitmaster.util.constants.ErrorConstants;

import java.util.Locale;
import java.util.ResourceBundle;

public class DescriptionUtils {
    public static String errorDescriptionPath = "desc/" + getAppName() + "/error_description";
    public static String actionDescriptionPath = "desc/" + getAppName() + "/action_description";

    public static String getActionDescriptionPath(String key) {
        Locale locale = new Locale(getAppLocale());
        ResourceBundle actionResourceBundle = ResourceBundle.getBundle(actionDescriptionPath, locale);

        try {
            return actionResourceBundle.getString(key);
        } catch (Exception e) {
            return "Action successfully completed";
        }
    }

    public static String getErrorDescription(String key) {
        Locale locale = new Locale(getAppLocale());
        ResourceBundle errorResourceBundle = ResourceBundle.getBundle(errorDescriptionPath, locale);

        try {
            return errorResourceBundle.getString(key);
        } catch (Exception e) {
            return  "An error occurred.";
        }
    }

    public static String getInterpolatedErrorDescription(String field, String message) {
        return getErrorDescription(message).replace("{placeholder}", ('\'' + field + '\''));
    }

    public static String getErrorDescription(ErrorConstants errorConstants) {
        return getErrorDescription(errorConstants.toString());
    }

    public static String getAppName() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        try {
            return bundle.getString("app.name");
        } catch (Exception e) {
            return ApplicationConstants.DEFAULT_APP_NAME;
        }
    }

    public static String getAppLocale() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        try {
            return bundle.getString("app.locale");
        } catch (Exception e) {
            return ApplicationConstants.DEFAULT_APP_LOCALE;
        }
    }

    public static String getAppVersion() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        try {
            return bundle.getString("app.version");
        } catch (Exception e) {
            return ApplicationConstants.DEFAULT_APP_VERSION;
        }
    }

    public static String getAppTheme() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        try {
            return bundle.getString("app.theme");
        } catch (Exception e) {
            return ApplicationConstants.DEFAULT_APP_THEME;
        }
    }
}
