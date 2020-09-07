package day03;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;

public class ExtractingDataOutOfResponseBody {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        // provide your baseURI in the test
        // add query parameters
        // apikey = ypurapikey
        //t = Boss Baby
        //Save the response into response object

      Response response = given()
              .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey", "9255cfd7")
                .queryParam("t", "Boss Baby").
        when()
                .get();


     response.prettyPrint();
//      response.statusCode();
     response.asString();



      // if you want to get a single data out, for exam: just year
        // use path method of Response object and provide the jsonPath

        String title = response.path("Title"); // will get single data
        System.out.println("title = " + title);
        String year = response.path("Year");
        System.out.println("year = " + year);
        String release = response.path( "Released");
        System.out.println("release = " + release);
        String plot = response.path("Plot");
        System.out.println("plot = " + plot);
        String firstratingsSource = response.path("Ratings[0].Source");
        System.out.println("ratings = " + firstratingsSource);
        String lastratingValue = response.path("Ratings[-1].Value");
        System.out.println("lastratingValue = " + lastratingValue);
        String metascore = response.path("Metascore");
        System.out.println("metascore = " + metascore);
        String production = response.path("Production");
        System.out.println("production = " + production);


    }






}
