package day05;

import POJO.Spartan;
import io.restassured.RestAssured;
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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

public class SecureSpartanTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://23.23.75.140";
        port =8000;
        RestAssured.basePath = "/api";
    }


    @DisplayName("get single spartan without credentials")
    @Test
    public void getSingleSpartanTestWithoutCred(){



        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 35).

                when()
                .get("/spartans/{id}").

                then()
                .log().all()
                .statusCode(401);

    }


    @Test
    public void getSingleSpartanTest(){

        int newId = createRandomSpartan();
        given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
               .auth().basic("admin", "admin")
                .pathParam("id", newId).

        when()
                .get("/spartans/{id}").

        then()
                .log().all()
                .statusCode(200);

    }


    public static int createRandomSpartan(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone =  faker.number().numberBetween(1000000000L, 9999999999L);

        Spartan sp = new Spartan(name, gender, phone);

        Response response = given()
                                   .auth().basic("admin", "admin")
                                   .contentType(ContentType.JSON)
                                    .body(sp).

                            when()
                                    .post("/spartans")
                             .prettyPeek();


        return response.path("data.id");

    }



}
