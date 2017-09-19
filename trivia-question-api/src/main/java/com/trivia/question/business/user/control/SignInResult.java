package com.trivia.question.business.user.control;

import java.util.HashMap;

public class SignInResult {

    public enum Status {
        SUCCESS,
        EMAIL_NOT_FOUND,
        WRONG_PASSWORD,
        EMAIL_INVALID
    }

    private HashMap<String, String> data;
    private Status status;

    private SignInResult(){

    }

    public static SignInResult success(HashMap<String, String> data){
        SignInResult signInResult = new SignInResult();
        signInResult.data = data;
        signInResult.status = Status.SUCCESS;
        return signInResult;
    }

    public static SignInResult fail(Status status){
        SignInResult signInResult = new SignInResult();
        signInResult.status = status;
        return signInResult;
    }

    public Status getStatus() {
        return status;
    }

    public HashMap<String, String> getData(){
        return this.data;
    }

    public boolean hasFailed(){
        return status != Status.SUCCESS;
    }
}
