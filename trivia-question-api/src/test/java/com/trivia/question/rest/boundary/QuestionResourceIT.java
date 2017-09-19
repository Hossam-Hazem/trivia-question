package com.trivia.question.rest.boundary;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionResourceIT {

    private static final String HOST = "localhost";
    private static final String SCHEME = "https";
    private static final int PORT = 8080;

    private static final URI URI = UriBuilder.fromUri("").scheme(SCHEME).host(HOST).port(PORT)
            .path("trivia-question").path("api").build();

    private static final String QUESTION_PATH = "/question";

    @Test
    public void testListRoles() throws Exception {
        Client client = ClientBuilder.newClient();
        Response response = null;
        try {
            response = client.target(URI).path(QUESTION_PATH)
                    .request()
                    .get();
            System.out.println(response.getHeaders());
            System.out.println(response.getCookies());
            System.out.println(response.readEntity(String.class));
            System.out.println(response.getStatus());
        } finally {
            if (response != null)
                response.close();
            client.close();
        }
    }
}
