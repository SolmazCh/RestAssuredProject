package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;


public class RestAssurredMethodChaining {

    @DisplayName("First Attemp for chaining")
    @Test
    public void chainingMethodsTest1(){

        //  given().
        // some more information you want to provide other than request url
        // like header, query param, path variable, body
        // when()
        // you sent the request  GET POST PUT PATCH DELETE
        //THEN()
        //VALIDATE SOMETHING HERE


        //http://54.159.3.135:8000/api/hello

        when().
                get("http://54.159.3.135:8000/api/hello"). // SENDING A REQUEST TO THIS URL
        then().                    //ASSERTIONS START HERE
                statusCode(is(200)).   //ASSERTING STATUS CODE IS 200
                header("Content-Length", equalTo("17")); // ASSERTING HEADER IS 17

    }


    @DisplayName("Using Hamcrest matcher for assertion")
    @Test
    public void testingWithMatcher(){

        when().
                get("http://54.159.3.135:8000/api/hello"). // SENDING A REQUEST TO THIS URL
         prettyPeek().
         then().                    //ASSERTIONS START HERE
                statusCode(is(200)).   //ASSERTING STATUS CODE IS 200
                header("Content-Length", equalTo("17")). // ASSERTING HEADER IS 17
                header("Content-Type", "text/plain;charset=UTF-8").
                body(is("Hello from Sparta"));


    }


    @DisplayName("Testing get /api/spartans endpoint")
    @Test
    public  void testAllSpartans(){
        // given part -- RequestSpesification

        //when part -- send request(get, put delete, post..)

        // then part -- ValidatableResponse
        // this is where assertions start
        //you can chain multiple assrtions
        // if any assertions fail, whole test fail


          // given() --> providing more information
        given().  // add all your request specific information like header, query param, path var, body
                header("Accept", "application/xml").
        when().
                get("http://54.159.3.135:8000/api/spartans").
                prettyPeek().
        then(). //then() --> giving the validation
                statusCode(is(200));
    }





}