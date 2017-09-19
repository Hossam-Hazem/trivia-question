package com.trivia.question.business.choice.boundary;

import com.trivia.question.business.persistence.boundary.TriviaQuestion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class ChoiceService {

    @Inject
    @TriviaQuestion
    protected EntityManager em;

}
