package com.trivia.question.rest.boundary;

import com.trivia.question.business.Constants;
import com.trivia.question.business.question.boundary.QuestionService;
import com.trivia.question.business.question.entity.Question;

import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;


@Path("questions")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {

    @EJB
    private QuestionService roleService;

    @POST
    public Response createQuestion(JsonObject jsonObject){
        String questionName = jsonObject.getString("name");
        List<Integer> topicsId = new LinkedList<>();
        for(JsonValue topicJson : jsonObject.getJsonArray("topics")){
            topicsId.add(((JsonObject) topicJson).getInt("id"));
        }

        List<String> choicesString = new LinkedList<>();
        for(JsonValue choiceJson : jsonObject.getJsonArray("choices")){
            choicesString.add(((JsonObject) choiceJson).getString("text"));
        }

        int correctChoiceId = jsonObject.getInt("correctChoice");

        try {
            return Response.ok(roleService.createQuestion(questionName, topicsId, choicesString, correctChoiceId)).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @GET
    public Response findAll() {
        try {
            return Response.ok(roleService.findAll()).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Path("{questionId}")
    @GET
    public Response getQuestion(@PathParam("questionId") int questionId){
        try{
            return Response.ok(roleService.getQuestionById(questionId)).build();
        }
        catch (Exception exc){
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

}
