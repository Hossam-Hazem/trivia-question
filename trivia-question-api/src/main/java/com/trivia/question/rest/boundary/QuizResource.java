package com.trivia.question.rest.boundary;

import com.trivia.question.business.Constants;
import com.trivia.question.business.quiz.boundary.QuizService;

import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("quizzes")
@Produces(MediaType.APPLICATION_JSON)
public class QuizResource {

    @EJB
    QuizService quizService;

    @POST
    @Path("start")
    public Response startQuiz(JsonObject jsonObject){
        try{
            int userId = jsonObject.getInt("user_id");
            int topicId = jsonObject.getInt("topic_id");

            return Response.ok(quizService.start(topicId, userId)).build();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @POST
    @Path("submit")
    public Response submit(JsonObject jsonObject){
        try{
            int quizId = jsonObject.getInt("quiz_id");
            return Response.ok(quizService.submitReview(quizId)).build();
//            return Response.ok(quizService.submit(quizId)).build();
        }

        catch (Exception exc){
            System.out.println(exc.getMessage());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @POST
    @Path("submitReview")
    public Response submitReview(JsonObject jsonObject){
        try{
            int quizId = jsonObject.getInt("quiz_id");
            return Response.ok(quizService.submitReview(quizId)).build();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @POST
    @Path("setScore")
    public Response setScore(JsonObject jsonObject){
        try{
            System.out.println(jsonObject);
            int quizId = Integer.parseInt(jsonObject.getString("quizId"));
            int score = Integer.parseInt(jsonObject.getString("score"));
            return Response.ok(quizService.setScore(quizId, score)).build();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @GET
    @Path("score/{userId}/{quizId}")
    public Response getScore(@PathParam("quizId") int quizId, @PathParam("userId") int userId){
        try{
            return Response.ok(quizService.getScore(quizId, userId)).build();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @GET
    public Response getQuizzes(@QueryParam("userId") int userId){
        try{
            return Response.ok(quizService.getQuizzes(userId)).build();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

}
