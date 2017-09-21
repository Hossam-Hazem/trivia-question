package com.trivia.question.business.quiz.control;

import com.trivia.question.CaseManagerConnector;
import com.trivia.question.business.answer.entity.Answer;
import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.quiz.entity.Quiz;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class QuizControl {

    public int calculateScore(List<Choice> userChoices){
        int score = 0;
        for(Choice userChoice : userChoices){
            if(userChoice.isCorrect())
                score++;
        }
        return score;
    }

    public boolean questionExists(Quiz quiz, Question question) {
        for(Question quizQuestion : quiz.getQuestions()){
            if(quizQuestion.equals(question)){
                return true;
            }
        }
        return false;
    }

    public void CreateQuizReviewCase(Quiz quiz){
//        List<Answer> answers = quiz.getAnswers();
        CaseManagerConnector caseManagerConnector = new CaseManagerConnector();
        List<String> questionsStr = new LinkedList<>();
        List<String> answersStr = new LinkedList<>();
        for(Question question : quiz.getQuestions()){
            questionsStr.add(question.getName());
        }
        for(Answer answer : quiz.getAnswers()){
            answersStr.add(answer.getChoice().getText());
        }
        caseManagerConnector.createReviewQuizCase(quiz.getUser().getId(), questionsStr, answersStr);
    }
}
