package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class SpartanTest2 {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Get 1 Spartan Test")
    @Test
    public void testingSingleSpartan(){

        //I want to log the request I sent so I see what is the URL, methods and so on
        given()
         //       .log().parameters().
                .log().all().
         when()
                .get("/spartans/10").
       //          .prettyPeek().
         then()
                .log().headers()
               // .log().body()
                //.log().everything()
                .log().ifValidationFails()
                 .statusCode(is(200));

    }





    //quick tasks
    // add another test for hello endpoint by reusing the baseURI, basePath above
    // specify you want to get a text result back
    // check status code is 200
    // contentType is Text
    // body is Hello from Sparta

    @DisplayName("Test Hello spartan")
    @Test
    public void testHello(){
        given()
                .accept( ContentType.TEXT).

        when()
                .get("/hello").

         then()
                 .statusCode(is(200)).
                 contentType(ContentType.TEXT).
                body(is("Hello from Sparta"));
    }

    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans(){

        // it will create the request URL as is
        //baseURI + basePath + what is after get("This Part")

        // I want to explicitly specify I want JSON response from this result
        given()
                .header("Accept", "application/json");

        when()
                .get("/spartans").
         then()
                .statusCode(is(200));

    }

    @DisplayName("Get All Spartans 2")
    @Test
    public void testAllSpartans2(){

        //sen the same request spesicifacion the accept header is application/json
        // use baseuri, basepath, check status code 200, contentType header is json

        given()
                .accept(ContentType.JSON ).
         when()
                 .get("/spartans").
         then()
                  .statusCode(is(200)  )
                 // .contentType("application/json; charset=utf-8")
                //  .contentType(is("application/json; charset=utf-8"))
                // easiest way for content type is using ContentType enum :
                  .contentType(ContentType.JSON);

    }

}
