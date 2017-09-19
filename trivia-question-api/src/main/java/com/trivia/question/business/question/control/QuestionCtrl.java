package com.trivia.question.business.question.control;

import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.topic.entity.Topic;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class QuestionCtrl {

    @Inject
    @TriviaQuestion
    protected EntityManager em;

    public List<Question> getQuestionsByTopicId(int topicId, int limit){
        return em.createQuery("SELECT q FROM Question q JOIN q.topics topic WHERE Topic.id = :topicId" , Question.class)
                .setParameter("topicId", topicId)
                .setMaxResults(limit)
                .getResultList();
    }
}
