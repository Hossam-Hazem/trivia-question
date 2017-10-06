package com.trivia.question.business;

import java.util.regex.Pattern;

public final class Constants {

    public static final String UNIT_NAME = "triviaquestionPU";

    public static final int QUESTIONS_LIMIT = 3;

    public  static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public  static final String USERID = "p8admin3";

    /**
     * See comments for USERID.
     */
    public  static final String PASSWORD = "P@ssw0rd";

    public  static final String CE_URI = "http://10.0.1.66:9080/wsi/FNCEWS40MTOM";

    public  static final String OBJECT_STORE_NAME = "TARGET";
}
