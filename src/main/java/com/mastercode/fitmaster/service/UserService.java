package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.util.DescriptionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    public Map<String, String> getAppInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("appName", DescriptionUtils.getAppName());
        info.put("appLocale", DescriptionUtils.getAppLocale());
        info.put("appVersion", DescriptionUtils.getAppVersion());
        info.put("appTheme", DescriptionUtils.getAppTheme());
        return info;
    }
}