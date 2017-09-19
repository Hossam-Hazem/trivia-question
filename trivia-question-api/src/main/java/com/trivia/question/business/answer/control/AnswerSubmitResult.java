package com.trivia.question.business.answer.control;

import com.trivia.question.business.answer.entity.Answer;

import java.util.HashMap;

public class AnswerSubmitResult
{
    public enum Status{
        SUBMITTED, QUESTION_ALREADY_SUBMITTED, NOT_BELONG_TO_QUIZ
    }

    private Status status;
    private HashMap<String, Object> data = new HashMap<>();

    private AnswerSubmitResult(){

    }

    public static AnswerSubmitResult success(HashMap<String, Object> data){
        AnswerSubmitResult answerSubmitResult = new AnswerSubmitResult();
        answerSubmitResult.status = Status.SUBMITTED;
        answerSubmitResult.data = data;
        return answerSubmitResult;
    }

    public static AnswerSubmitResult failQuestionSubmitted(String message){
        AnswerSubmitResult answerSubmitResult = new AnswerSubmitResult();
        answerSubmitResult.status = Status.QUESTION_ALREADY_SUBMITTED;
        answerSubmitResult.data.put("message", message);
        return answerSubmitResult;
    }

    public static AnswerSubmitResult failQuizNotBelong(String message){
        AnswerSubmitResult answerSubmitResult = new AnswerSubmitResult();
        answerSubmitResult.status = Status.NOT_BELONG_TO_QUIZ;
        answerSubmitResult.data.put("message", message);
        return answerSubmitResult;
    }

    public Status getStatus() {
        return status;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
