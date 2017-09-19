package com.trivia.question.business.question.boundary;

import com.trivia.question.business.question.control.QuestionCtrl;
import com.trivia.question.business.question.entity.Question;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.NoSuchElementException;
import javax.inject.Inject;
import com.trivia.question.business.persistence.boundary.TriviaQuestion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;

@Stateless
public class QuestionService {

    @Inject
    @TriviaQuestion
    protected EntityManager em;

    public List<Question> findAll() {
        return em.createQuery("SELECT q FROM Question q" , Question.class)
                .getResultList();
    }

    /*
        just a test
     */
    public Question getQuestionById(int id) throws Exception{
        Question question =  em.find(Question.class, id);

        if(question == null)
            throw new NoSuchElementException();

        return question;

    }

    public JsonObject createQuestion(String questionName, List<Integer> topicsId, List<String> choicesString, int correctChoiceId) {


        //mfeesh wa2t
        return null;
    }
}
