package day01;


import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1 {



    @Test
    public void test1(){
       // RestAssured.get("URL here");
        //since we did static import
        //we can directly call the method from RestAssured class
        //after we send request n
        // we can save the result in to a type called Response
        Response response = get("http://54.159.3.135:8000/api/hello");
        //getting status code
        System.out.println("Status code of this response: "+ response.statusCode());
        //this is another way to get status with HTTP/1.1
        System.out.println("getting status line of this response "+response.statusLine());

        // in restAssured there are usually 2 methods that does same action
        //one directly with the name like response.statusCode()
        // another stating with getXXX like response.getStatusCode();
          System.out.println("Status code of this response: "+response.getStatusCode());

          // we can use Response.getHeader("header name goes here") or Response.header("header name goes here") --> doing the same thing
        System.out.println("Getting the value of date header: "+ response.header("Date"));
        System.out.println("Getting the value of Content-Type header: "+ response.getHeader("Content-Type"));
        System.out.println("Getting the value of Accept header: "+ response.header("Accept"));
        System.out.println("Getting the value of Content-Length header: "+ response.getHeader("Content-Length"));


        //getting Content-Type header value
        System.out.println("Content-Type value: "+response.contentType()); // return the value of content-type header
        System.out.println(response.getContentType().length());
    }

    @DisplayName("Testing /hello/ endpoint")
    @Test
    public void testHello(){
        Response response = get("http://54.159.3.135:8000/api/hello");
        // testing the status code returned correctly
        assertEquals(200, response.statusCode());

        // testing the Content-Type header value is:
        assertEquals("text/plain;charset=UTF-8", response.contentType());

        // // testing the Content-length header value is: "17"
        assertEquals("17", response.header("Content-length"));

    }


    @DisplayName("Testing/hello endpoint body")
    @Test
    public  void testingHelloBodyResponse(){

        // get the body and assert the body equal Hello from Sparta
        Response response = get("http://54.159.3.135:8000/api/hello");

        //getting the body as String using asString() method of Response object
        System.out.println( response.asString() );
        //getting the body by calling body method
        System.out.println(response.body().asString());

        // assert the body is Hello from Sparta , length is 17

        assertEquals(response.asString(), "Hello from Sparta");
        assertEquals(17, response.asString().length());

    }

     @DisplayName("Printing the response body using method")
    @Test
    public void printingBody(){
         Response response = get("http://54.159.3.135:8000/api/hello");
         //easy way to print the response body and return at the same time --> will return only body  as String
         response.prettyPrint();

         System.out.println("==============================================");
         // another way to see the body quick is prettyPeek --> will return Response type, not just body
         // it will include all header, status code, body
         //it will enable you to call more method of response object after peeking
         response.prettyPeek();

         System.out.println("=======================================================");
         // I want to see entire response + save the status code into a variable in same statement

         int statusCode = response.prettyPeek().statusCode();
         System.out.println("Printing only status code: "+statusCode);




     }


}