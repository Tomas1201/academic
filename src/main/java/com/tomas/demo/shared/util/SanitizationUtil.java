package com.tomas.demo.shared.util;

public final class SanitizationUtil {

    private SanitizationUtil() {
    }

    public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        return input.trim();
    }
}
