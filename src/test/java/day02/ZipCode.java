package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;


@DisplayName(" Testing Zip code API")
public class ZipCode {
    //	    Add baseURI as api.zippopotam.us
    //	    	basePath as /us/
    //	    under @BeforeAll
    //	    add
    //	    	path variable {zipcode} in given section
    //	    send Get request
    //	    then  check the status code 200
    //	    check the contentype header is json
    //	         body : post code -- the zipcode you entered
    //	         		country  United States
    //	         	    longitude  -- the expected value
    //	         	    state    --  the expected value

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://api.zippopotam.us";
        RestAssured.basePath = "/us";
    }


    @Test
    @DisplayName("Zip to City Test")
    public void ZipToCityTest(){


        given()
                .pathParam("zipcode", 22030).
                log().all().
        when()
                 .get("/{zipcode}").
        then()
                .log().all()
                .statusCode(is(200)).
                contentType(ContentType.JSON).
              //body(" 'post code' ", is(22030)).
                body("country", is("United States"))
                .body("places[0].longitude", is( "-77.3242"))
               .body("places[0].state", is("Virginia"))
                .body("places[0].'place name' ", is("Fairfax"));

             // if a field has spase then add '  '  for example: " 'post code' "

    }


    @DisplayName("City to Zip")
    @Test
    public void testCityToZip(){

        given()
              //  .pathParam("state", "VA").
              //  pathParam("city", "Fairfax").
                .log().all().

        when()
                 .get("/{state}/{city}", "VA", "Fairfax").
        then()
                .log().all()
                .statusCode(is(200)).
                contentType(ContentType.JSON)
                .body("'country abbreviation'", is("US"))
                .body("places[0].'place name'", is("Fairfax"))
                .body("places[0].latitude", is( "38.8458"))
                .body("places[1].longitude", is("-77.2649"))
                .body("places[2].'post code'", is("22032"))
                .body("country", is("United States"))
                .body("'place name'",is("Fairfax"))
                // jsonPath hack for getting last item from jsonArray
                // I want to get last post code from the body
                // -1 index indicate the last item, only works here not in postman
                .body("places[-1].latitude", is( "38.7602"));





    }




}
