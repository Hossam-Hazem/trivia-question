package com.trivia.question.business.topic.boundary;

import com.trivia.question.business.Constants;
import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.topic.control.TopicCtrl;
import com.trivia.question.business.topic.entity.Topic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class TopicService {

    @Inject
    @TriviaQuestion
    protected EntityManager em;

    @EJB
    TopicCtrl topicCtrl;

    public List<Topic> findAll(){
        return em.createQuery("SELECT t FROM Topic t" , Topic.class)
                .getResultList();
    }

    public List<Question> getQuestionsByTopicId(int id, int limit) {
        List<Question> questions = em.find(Topic.class, id).getQuestions();
        if(limit > 0)
            return topicCtrl.getRandomQuestions(questions, Constants.QUESTIONS_LIMIT);
        else
            return questions;
    }

    public boolean isTopicExist(int id){
        return em.find(Topic.class,id) != null;
    }
}
