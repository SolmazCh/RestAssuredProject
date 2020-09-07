package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class Practice1 {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135";
        RestAssured.port =8000;  // we can write port separately too
        RestAssured.basePath = "/api";
    }

    @DisplayName("simple test")
    @Test
    public void test1(){

        given()
                .log().all()
                .queryParam("gender", "Female").

        when()
                .get("/spartans/search").
        then()
        .statusCode(200)    ;
    }




}
