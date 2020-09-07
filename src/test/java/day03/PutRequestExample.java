package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
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
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;


public class PutRequestExample {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Updating existing data")
    @Test
    public void UpdateSpartan() {

        String updateBody = "{\n" +
                "    \"id\": 10,\n" +
                "    \"name\": \"Changing\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 3312820936\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .log().all().

         when()
                 .put("/spartans/{id}", 10).

         then()
                  .log().all()
                  .statusCode(204);
    }


    @Test
    public  void testDelete(){
        when()
                .delete("/spartans/{id}", 10).
        then()
                .statusCode(204)
                ;
    }

}
