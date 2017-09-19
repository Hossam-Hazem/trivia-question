package com.trivia.question.business.quiz.control;

import com.trivia.question.business.question.entity.Question;

import java.util.HashMap;
import java.util.List;

public class QuizStartResult {
    public enum Status{
        SUCCESS, FAIL
    }

    private Status status;
    private HashMap<String, Object> data = new HashMap<>();

    private QuizStartResult(){
    }

    public static QuizStartResult success(HashMap<String, Object> data){
        QuizStartResult quizStartResult = new QuizStartResult();
        quizStartResult.status = Status.SUCCESS;
        quizStartResult.data = data;
        return quizStartResult;
    }

    public static QuizStartResult fail(String message){
        QuizStartResult quizStartResult = new QuizStartResult();
        quizStartResult.status = Status.FAIL;
        quizStartResult.data.put("message", message);
        return quizStartResult;
    }

    public Status getStatus() {
        return status;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
