package com.trivia.question.business.quiz.control;


import java.util.HashMap;

public class QuizResult {
    public enum Status {
        FINISHED,
        NOT_COMPLETE
    }
    private HashMap<String, String> data;
    private QuizResult.Status status;

    public static QuizResult finished(HashMap<String, String> data){
        QuizResult quizResult = new QuizResult();
        quizResult.data = data;
        quizResult.status = Status.FINISHED;
        return quizResult;
    }

    public static QuizResult notFinished(){
        QuizResult quizResult = new QuizResult();
        quizResult.status = Status.NOT_COMPLETE;
        return quizResult;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }
}
