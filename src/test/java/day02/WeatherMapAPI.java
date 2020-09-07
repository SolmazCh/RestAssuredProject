package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WeatherMapAPI {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="https://api.openweathermap.org";
        RestAssured.basePath="/data/2.5/";

    }


    @DisplayName("Forecast by cityName")
    @Test
    public void byCityName(){

        given()
                .log().all()
                .queryParam("q", "London")
                .queryParam("apikey", "a6c9376fe026264fa62ca78e10263a35").
         when()
                .get("weather").
        then()
                .log().all()
                .statusCode(is(200))
                .body("weather[0].main", is("Clouds"))
                .body("sys.type", is(1));



    }


}
