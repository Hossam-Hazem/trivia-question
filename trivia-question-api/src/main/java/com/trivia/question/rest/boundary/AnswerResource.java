package com.trivia.question.rest.boundary;

import com.trivia.question.business.Constants;
import com.trivia.question.business.answer.boundary.AnswerService;

import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("answers")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerResource {

    @EJB
    AnswerService answerService;

    @POST
    @Path("submit")
    public Response submitAnswer(JsonObject jsonObject){
        try{
            int quizId = jsonObject.getInt("quiz_id");
            int choiceId = jsonObject.getInt("choice_id");
            return Response.ok(answerService.submitAnswer(quizId, choiceId)).build();
        }
        catch (Exception exc){
            System.out.println(exc.toString());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

}
