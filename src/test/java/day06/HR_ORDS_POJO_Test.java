package day06;

import POJO.Location;
import POJO.Region;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
public class HR_ORDS_POJO_Test {

    @BeforeAll
    public static void setUp(){
        baseURI="http://54.196.47.112";
        port=1000;
        basePath="ords/hr";
    }

    @DisplayName("Testing the /locations/{location_id} endpoint")
    @Test
    public void testLocation() {


     Response response=   given()
                .accept(ContentType.JSON)
                .pathParam("location_id", 1700)
                .log().all().
         when()
                 .get("locations/{location_id}")
                 .prettyPeek();

        Location location = response.as(Location.class);

        System.out.println(location);

    }


    @DisplayName("Testing the /locations endpoint")
    @Test
    public void testLocations(){

        Response response = get("locations");
        response.prettyPeek();

        List<String> strreetAddress = response.path("items.street_address");
        System.out.println("streetsList: "+strreetAddress);


        Location location = response.as(Location.class);
        System.out.println("Location: "+location);


        List<Location> lisfOfLocations = response.jsonPath().getList("items", Location.class);
        for(Location each: lisfOfLocations) {
            System.out.println( each);
        }
        //  lisfOfLocations.forEach(each -> System.out.println("Location = " + each));

        assertThat(lisfOfLocations, hasSize(23));


        String linksHref = response.path("links[0].href");

        System.out.println("linksHref = " + linksHref);


       // we want to get list of pojo but we only want to get those
        // data with country_id as US
        List<Location> usLocations
                =response.jsonPath().getList("items.findAll{it.country_id=='US' }", Location.class);



    }


            }
