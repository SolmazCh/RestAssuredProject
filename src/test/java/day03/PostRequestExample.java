package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PostRequestExample {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }



    @DisplayName("Testing Post spartan")
    @Test
    public void testAddSpartan(){

        String myBodyData ="{\n" +
                "\"name\" : \"Tural\",\n" +
                "\"gender\": \"Male\",\n" +
                "\"phone\": 36278827113\n" +
                "}";

        System.out.println("myBodyData = " + myBodyData);

        given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
         then()
                .log().all()
                 .statusCode(201).
                  contentType(ContentType.JSON)
                  .body("success", is("A Spartan is Born!"))
                 .body("data.name", is("Tural"));

    }


    @DisplayName("Practice extracting data")
    @Test
    public void posrRequestExtractingData(){
        //// make a post request , store the response Object 
        //// save the id into int variable 
        //// save the name into String 
        //// print them out. 

        String myBodyData ="{\n" +
                "\"name\" : \"Tural\",\n" +
                "\"gender\": \"Male\",\n" +
                "\"phone\": 36278827113\n" +
                "}";

        System.out.println("myBodyData = " + myBodyData);

      Response response =  given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
                when()
                .post("/spartans")
                .prettyPeek();


        JsonPath jp = response.jsonPath();

        int id = jp.getInt("data.id");
        System.out.println("id = " + id);

        String name = jp.getString("data.name");
        System.out.println("name = " + name);
    }

}
