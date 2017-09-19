package com.trivia.question.business.user.control;


import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.user.entity.User;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.HashMap;

@Stateless
public class UserCtrl {



    public SignInResult signIn(User user, String hashedPassword){

        if(hashedPassword.equals(user.getPassword())){

            HashMap<String, String> data = new HashMap<>();
            data.put("name", user.getName());
            data.put("id", user.getId() + "");
            data.put("isAdmin", user.isAdmin()+"");
            return SignInResult.success(data);
        }
        else{
            return SignInResult.fail(SignInResult.Status.WRONG_PASSWORD);
        }
    }
}
