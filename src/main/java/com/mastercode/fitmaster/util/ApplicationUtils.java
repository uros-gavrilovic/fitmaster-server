package com.mastercode.fitmaster.util;

import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class ApplicationUtils {
    //    @Value("dev")
    private String activeProfile;

    public String getApplicationPropsByKey(String key) {
        ResourceBundle appProps = ResourceBundle.getBundle(
                activeProfile == null ? "application" : "application-" + activeProfile);
        return appProps.getString(key);
    }
}
