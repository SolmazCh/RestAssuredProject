package day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Testing/spartans/{id}")
    @Test
    public void testSingleSpartan(){

        given()
                .log().all()
                .pathParam("id", 10). // giving parameter as variable -> path parameter
        when()
                .get("spartans/{id}").
        then()
                 .statusCode(is(200));

    }

    @DisplayName("Testing2/spartans/{id}")
    @Test
    public void testSingleSpartan2(){

        given()
                .log().all().
        when()
                .get("spartans/{id}", 10).
        then()
                .statusCode(is(200));
    }


    @DisplayName("Testing3/spartans/{id} Body")
    @Test
    public void testingSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id", 10).
        when()
                .get("spartans/{id}").
        then()
                .log().all()
                .statusCode(is(200))
        //.body("JSON PATH", is("THE VALUE"))
        .body("id", is(10))
        .body("gender", is("Female"))
        .body("name", is("Change Name"))
        .body("phone", is(3312820936L) )
                ;


    }








}
