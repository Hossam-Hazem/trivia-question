package com.trivia.question.business.user.control;

import javax.ejb.Stateless;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.trivia.question.business.Constants.VALID_EMAIL_ADDRESS_REGEX;

@Stateless
public class EmailValidator {

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
