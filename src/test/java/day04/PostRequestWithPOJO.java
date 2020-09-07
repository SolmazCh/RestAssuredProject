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

public class PostRequestWithPOJO {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


    @Test
    public void TestBodyWithPOJO(){

        Spartan sp1 = new Spartan("Li", "Female", 1526377625);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
        when()
                .post("/spartans").
         then()
                .log().all()
                .statusCode(201);

    }


    @Test
    public void testPatchPartialUpdate(){
        //Only update the name with faker
        String randomName = new Faker().name().firstName();
       Map<String, Object> patchBodyMap = new HashMap<>();
       patchBodyMap.put("name", randomName);

       given()
               .log().all()
               .body(patchBodyMap)
               .contentType(ContentType.JSON).
        when()
                .patch("spartans/{id}", 45).

         then()
                .statusCode(204)
               .log().all();
    }





}
