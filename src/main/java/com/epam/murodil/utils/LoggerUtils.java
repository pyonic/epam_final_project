package com.epam.murodil.utils;

import jakarta.servlet.http.HttpServletRequest;

public class LoggerUtils {
    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    public static String getUrlInfoForLogger(HttpServletRequest request) {
        return String.format("%s -> %s", request.getMethod(), getFullURL(request));
    }
}
