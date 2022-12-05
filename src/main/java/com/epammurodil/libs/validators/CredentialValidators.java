package com.epammurodil.libs.validators;

import com.epammurodil.constants.ErrorConstants;
import com.epammurodil.constants.QueryConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialValidators {
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    public static boolean validateEmail (String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Map<String, String> validatePassword (String password) {
        Map<String, String> resultMap = new HashMap<>();

        if (password.length() > ErrorConstants.PASSWORD_MAX_LENGTH || password.length() < ErrorConstants.PASSWORD_MIN_LENGTH) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.PASSWORD_MIN_MAX_LENGTH_ERROR);
        }

        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars )) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.NEED_UPPERCASE);
        }

        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))  {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.NEED_LOWERCASE);
        }

        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers )) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.NEED_NUMBER);
        }

        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars )) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.NEED_SPECIAL_CHAR);
        }

        return resultMap;
    }
}
