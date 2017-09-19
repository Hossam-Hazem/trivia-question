package com.trivia.question.business.topic.control;

import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.topic.entity.Topic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.*;

@Stateless
public class TopicCtrl {

    public List<Question> getRandomQuestions(List<Question> questions, int limit){
        List<Question> selectorSet = new LinkedList<>(questions);
        questions = null;
        List<Question> result = new LinkedList<>();
        Random r = new Random(System.currentTimeMillis());
        for(int c = 0; c < limit; c++){
            int randomIndex = r.nextInt(selectorSet.size());
            result.add(selectorSet.remove(randomIndex));
        }
        return result;
    }

}
