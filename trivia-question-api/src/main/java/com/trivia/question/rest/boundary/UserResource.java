package com.trivia.question.rest.boundary;

import com.trivia.question.business.user.boundary.UserService;

import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @EJB
    UserService userService;

    @POST
    @Path("signin")
    @Consumes("application/json")
    public Response signIn(JsonObject json){
        String email = json.getString("email");
        String hashedPassword = json.getString("password");
        return Response.ok(userService.signIn(email, hashedPassword)).build();
    }
}
