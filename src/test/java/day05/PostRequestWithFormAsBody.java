package day05;

import org.junit.jupiter.api.BeforeAll;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestWithFormAsBody {

    //posting in library app
    // body is not json, it is x-www-urlencoded-form-data
    // http://library3.cybertekschool.com/rest/v1/login
    // baseURI http://library3.cybertekschool.com
    // basePath is /rest/v1
    // we are working on POST /login

    // post body, type x-www-urlencodedd-form-data
    // email:  librarian572@library
    //password 7tPIUop4


    //in URL if you don't see port, because it is omitted because it's so common
    // http -->80
    // https  --> 443
    // anything other than above 2 ports need to be explicitly set in the url
    // ex: spartan app use port 8000

    @BeforeAll
    public static void setUp(){
        baseURI ="http://library1.cybertekschool.com";
        basePath="/rest/v1";
    }



     @DisplayName("POST/login request test")
    @Test
    public void testLoginEndPoint(){
        given()
                .log().all()
                .formParam("email", "librarian69@library")
                .formParam("password","KNPXrm3S" ).

         when()
                .post("/login").

        then()
                .log().all()
                .statusCode(200)
                //we can not actually validate the token since it is dynamic
                // we can just validate that token is not null or empty
                .body("token", is(notNullValue()))
                 ;

    }

    public  static String loginAndGetToken(String email, String password){
      Response response =  given()
                .contentType(ContentType.URLENC)
                .formParam("email", email)
                .formParam("password", password).

        when()
                .post("/login");

       String token = response.path("token");

        return  token;
    }


    @DisplayName("Testing out the utility")
    @Test
    public void runningUtilityMethod(){
        loginAndGetToken("librarian69@library","KNPXrm3S" );
    }














}
