package com.trivia.question.business.answer.boundary;

import com.trivia.question.business.answer.control.AnswerControl;
import com.trivia.question.business.answer.control.AnswerSubmitResult;
import com.trivia.question.business.answer.entity.Answer;
import com.trivia.question.business.choice.boundary.ChoiceService;
import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.quiz.boundary.QuizService;
import com.trivia.question.business.quiz.entity.Quiz;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import java.util.HashMap;

@Stateless
public class AnswerService {

    @Inject
    @TriviaQuestion
    protected EntityManager em;

    @EJB
    QuizService quizService;

    @EJB
    AnswerControl answerControl;

    public AnswerSubmitResult submitAnswer(int quizId, int choiceId){

        Choice choice = em.find(Choice.class, choiceId);

        if(answerControl.isQuestionSubmitted(quizService.getAnsweredQuestions(quizId),choice.getQuestion()))
        {
            return AnswerSubmitResult.failQuestionSubmitted("question already submitted");
        }
        if(!quizService.doesQuestionBelong(quizId, choice.getId()))
        {
            return AnswerSubmitResult.failQuizNotBelong("choice does not belong to this quiz");
        }
        Answer answer = new Answer();
        answer.setChoice(choice);
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        answer.setQuiz(quiz);
        em.persist(answer);
        HashMap<String, Object> data = new HashMap<>();
        data.put("isCorrect", choice.isCorrect());
        return AnswerSubmitResult.success(data);
    }
}
