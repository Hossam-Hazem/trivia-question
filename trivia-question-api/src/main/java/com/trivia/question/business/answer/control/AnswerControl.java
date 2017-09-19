package com.trivia.question.business.answer.control;

import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.quiz.entity.Quiz;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AnswerControl {

    /*
        TODO find better class to belong
     */
    public boolean isQuestionSubmitted(List<Question> answeredQuestions, Question question){
        for(Question answeredQuestion : answeredQuestions){
            if(question.equals( answeredQuestion)){
                return true;
            }
        }
        return false;
    }
}
