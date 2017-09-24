package com.trivia.question.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class QuizWebService {

    @WebMethod
    public String sendScore(int score, int quizId){
        System.out.println("sendScore");
        System.out.println("score= "+score+" quizId= "+quizId);
        return "shrug";
    }
}
