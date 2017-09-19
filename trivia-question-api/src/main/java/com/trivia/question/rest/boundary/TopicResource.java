package com.trivia.question.rest.boundary;

import com.trivia.question.business.Constants;
import com.trivia.question.business.topic.boundary.TopicService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("topics")
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {

    @EJB
    TopicService topicService;

    @GET
    public Response findAll() {
        try {
            return Response.ok(topicService.findAll()).build();
        } catch (Exception exc) {

            return Response.serverError().entity(exc.getMessage()).build();
        }
    }


    @GET
    @Path("{id}/questions")
    public Response getQuestionsOfTopic(@PathParam("id") int id){
        try{

            return Response.ok(topicService.getQuestionsByTopicId(id, Constants.QUESTIONS_LIMIT)).build();
        }
        catch (Exception exc){
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

}
