/*
 * Copyright 2017 Sphere-Consulting
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trivia.question.business.persistence.control;

import com.trivia.question.business.Constants;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import javax.persistence.EntityManager;

/**
 * @author Ahmed El-mawaziny
 */
@Dependent
public class PersistenceCtrl implements Serializable {

    private static final long serialVersionUID = -6889158018923029225L;

    private EntityManager entityManager;

    @TriviaQuestion
    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext(unitName = Constants.UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
