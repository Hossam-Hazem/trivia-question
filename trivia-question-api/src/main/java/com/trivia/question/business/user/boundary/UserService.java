package com.trivia.question.business.user.boundary;

import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.user.control.EmailValidator;
import com.trivia.question.business.user.control.SignInResult;
import com.trivia.question.business.user.control.UserCtrl;
import com.trivia.question.business.user.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Stateless
public class UserService {

    @EJB
    protected UserCtrl userCtrl;

    @Inject
    @TriviaQuestion
    protected EntityManager em;

    public SignInResult signIn(String email, String hashedPassword) {
        email = email.toLowerCase();
        if(EmailValidator.validate(email)) {
            User user;
            try {
                user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                        .setParameter("email", email)
                        .getSingleResult();
            }
            catch(NoResultException e){
                return SignInResult.fail(SignInResult.Status.EMAIL_NOT_FOUND);
            }
            return userCtrl.signIn(user, hashedPassword);
        }
        else{
            return SignInResult.fail(SignInResult.Status.EMAIL_INVALID);
        }
    }

    public boolean isAdmin(int userId){
        User user = em.find(User.class, userId);
        return user.isAdmin();
    }
}
