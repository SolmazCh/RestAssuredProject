package day02;

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;


public class SpartanTest {


    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans(){

        String spartanURL = "http://54.159.3.135:8000/api/spartans";
        // how to set base URL, port, base path so I can reuse them
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";


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
                .baseUri("http://54.159.3.135:8000")
                .basePath("/api")
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
