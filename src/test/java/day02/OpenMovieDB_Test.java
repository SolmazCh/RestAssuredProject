package day02;

import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenMovieDB_Test {


    @BeforeAll
    public static void setUp(){
        baseURI ="http://www.omdbapi.com";
    }

    /// make a request
    //	      by adding few query parameters like
    //	      apikey =  your APIKEY
    //	      t =  the movie you want to search
    //	      plot =  full
    //	      then status code 200
    //	      		contentype is json
    //	      		body
    //	      			title is what you are searching for
    //	      			year is as you expected
    //	      			first rating value
    //	      			last rating value

    @DisplayName("Test Movie API")
    @Test
    public void searchMovie(){
        given()

                .queryParam("apikey","9255cfd7")
                .queryParam("t", "Golden Ring")
                .queryParam("plot", "full")
         .when()
                  .get()
        .then()
                .log().all()
        .statusCode(is(200))
        .contentType(ContentType.JSON)
        .body("Country", is("UK, USA")  )
                .body("Year", is("2018"))
                // containsString will search for part of title
                .body("Title", containsString("Golden"))
                .body("Type", is("movie"));


    }
}
