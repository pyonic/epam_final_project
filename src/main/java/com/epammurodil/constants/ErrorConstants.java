package com.epammurodil.constants;

public class ErrorConstants {
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 15;
    public static final String ERROR_KEY = "error";
    public static final String DUPLICATE_USER = "Error while inserting user, duplicate datas";
    public static final String DATA_NOT_PROVIDED = "Please send all details";
    public static final String PASSWORD_MISMATCH = "Password and confirmation password are not matching";
    public static final String EMAIL_INCORRECT = "Incorrect email passed";
    public static final String AUTH_FAILURE = "Authentication failed!";

    /* Validation errors */
    public static final String PASSWORD_MIN_MAX_LENGTH_ERROR = "Password must be less than 20 and more than 8 characters in length.";
    public static final String NEED_UPPERCASE = "Password must have at least one uppercase character";
    public static final String NEED_LOWERCASE = "Password must have at least one lowercase character";
    public static final String NEED_NUMBER = "Password must have at least one number";
    public static final String NEED_SPECIAL_CHAR = "Password must have atleast one special character among @#$%";
}
