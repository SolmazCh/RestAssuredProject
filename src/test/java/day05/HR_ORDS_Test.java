package day05;

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
import static org.hamcrest.Matchers.is;

public class HR_ORDS_Test {

    @BeforeAll
    public static void setUp(){
        baseURI="http://54.196.47.112";
        port=1000;
        basePath="ords/hr";
    }


    @DisplayName("Testing the /regions/{id} endpoint")
    @Test
    public void testRegion() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("region_id", 2).
                        when()
                .get("/regions/{region_id}")
                .prettyPeek();


        Region r1 = response.as(Region.class);
        System.out.println("r1 = " + r1);

    }
        @DisplayName("Testing the /regions endpoint")
         @Test
        public void testRegionJsonArrayToPojoList(){

           Response response = get("/regions").prettyPeek();
               JsonPath js = response.jsonPath();
           //get first region_name from the response using jsonPath
            String firstRegionName = response.path("items[0].region_name");
            System.out.println(firstRegionName);
            // get the last region_id from the response using jsonPath
            System.out.println(js.getInt("items[-1].region_id"));
            // get the list of region name from the response and save it to list>string
            List<String> list =js.getList("items.region_name");
            System.out.println(list);
            // get a list<region from the response json
            List<Region> objectList = js.getList("items", Region.class);

            System.out.println(objectList);


    }






}
