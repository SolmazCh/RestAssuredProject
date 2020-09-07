package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;


public class SpartanSearchTest_QuertParam {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Testing/spartans/search Endpoint")
    @Test
    public void testSearch(){

        given().
                log().all()
                .queryParam("gender", "male")
                .queryParam("nameContains", "li").

         when()
                 .get("spartans/search").

         then()
                .log().all()
                .statusCode(is(200))
                //assert number of elements according to your result here
                 .body("numberOfElements", is(16));


    }


}
