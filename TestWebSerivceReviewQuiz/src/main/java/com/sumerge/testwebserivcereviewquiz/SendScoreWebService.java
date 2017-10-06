/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumerge.testwebserivcereviewquiz;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Hossam
 */
@WebService
public class SendScoreWebService {
    @WebMethod
    public String sendScore(int score, int quizId){
        System.out.println("sendScore");
        try {
            StringEntity params =new StringEntity("{\"score\":"+score+",\"quizId\":"+quizId+"}");
            sendPostRequest(params);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendScoreWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "shrug";
    }   
    
    private static String sendPostRequest(StringEntity params){
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
        try {

        HttpPost request = new HttpPost("http://localhost:9080/trivia_question_api_war/api/quizzes/setScore");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        httpClient.execute(request);
        return "shrug";

        //handle response here...

        }catch (Exception ex) {

            //handle exception here
             ex.printStackTrace();
             return "shrug exception";
        }
        
    }
}
