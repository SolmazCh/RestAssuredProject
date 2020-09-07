package day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class HomeWork {
    //Data Drive your GET / api.zippopotam.us/us/:state/:city
    // Create a csv file called state_city.csv +
    // add 3 column  state , city , numberOfZipcodes+
    // VA ,  Fairfax , 9(send the request and prepare this number)
    // assert the state , city
    // and number of zipcodes you got from the response





@ParameterizedTest
@CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
public void testStateCity(String ExpectedState, String ExpectedCity, int expectednumOfZip){

   Response response= given()
            .baseUri("http://api.zippopotam.us" )
            .basePath("/us")
            .pathParam("state", ExpectedState)
            .pathParam("city", ExpectedCity)
            .log().all().

    when()
            .get("/{state}/{city}")
            .prettyPeek();


   assertThat(response.statusCode(), is(200));

    JsonPath jp = response.jsonPath();
     String state1 =jp.getString( "'state abbreviation'");

    System.out.println("state = " + state1);

    String city1 = jp.getString("'place name'");

    System.out.println("city1 = " + city1);

    assertThat(state1, is(ExpectedState));
    assertThat(city1, is(ExpectedCity));



    // now we want to count how many item in jsonArray from the respons e
    // and validate that against our csv file expected numbers
    // since post code key has space in between we have to add '' to treat it as one
  List<String> numOfZip = jp.getList("places.'post code'");

  assertThat(numOfZip,  hasSize(expectednumOfZip));


    // OPTIONALLY YOU MAY DO AS BELOW TO COUNT YOUR JSON ARRAY
    // if your jsonpath is pointing to an jsonArray you can count them
    // by called groovy method called size()
    System.out.println("calling the size method directly in jsonPath = "
            + jp.getInt("places.size()"));

}


    @Test
    public void testSingle(){

        Response response = given()
                .pathParam("state", "VA")
                .pathParam("city", "Fairfax")
                .baseUri("http://api.zippopotam.us/us").
                        when()
                .get("/{state}/{city}") ;
        // you can use then method to keep chaining your response assertions
        response.then().statusCode(200) ;


    }
}
