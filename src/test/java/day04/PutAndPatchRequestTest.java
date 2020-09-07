package day04;

import POJO.Spartan;
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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
public class PutAndPatchRequestTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Put Request body as a Map")
    @Test
    public void testPutRequestWithMap(){

        // put request to update spartan with id 421
        // name : put with map, gender: Male, phone
        //status code 204
        // how do I actually know it is updated since it does not have body in request
        // we can make another get request right after this and assert the body

        //getting random name:
        String randomName = new Faker().name().firstName();
        Map<String, Object> updatedBody = new LinkedHashMap<>();

        updatedBody.put("name", randomName);
        updatedBody.put("gender", "Male");
        updatedBody.put("phone", 123654896857L);


        //This is how we can provide POJO instead
        Spartan sp1 = new Spartan(randomName, "Female", 121212121212L);



        given()
                .log().all()
                .contentType(ContentType.JSON)
               // .body(updatedBody).  // this is how we do it with the map
                  .body(sp1).

         when()
                .put("/spartans/{id}", 37).
          then()
                .statusCode(204);


        when()
                .get("spartans/{id}", 37).

        then()
                .statusCode(200)
                .body("name", is(randomName));


    }

    //create a method that post a random spartan to the server
    // and return the id of that spartan, so you can always use a data that exists



}
