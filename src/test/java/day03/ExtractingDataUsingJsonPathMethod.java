package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractingDataUsingJsonPathMethod {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        Response response = given()
                .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey", "9255cfd7")
                .queryParam("t", "Boss Baby").
                        when()
                .get();


        // the JsonPath is a class that have a lot of methods
        // to get the body data in different format or data types
        // we get this object by calling the method of Response object called .jsonPath()
        JsonPath jp = response.jsonPath();
        //get the title as String
         String title = jp.getString("Title");

        System.out.println("Title = " + title);

        // get the year as int
        int year = jp.getInt("Year");
        System.out.println("year = " + year);

        String director = jp.getString("Director");
        System.out.println("director = " + director);
        
        
        // get the first rating source
        
        String firstRatingSource = jp.getString("Ratings[0].Source");
        System.out.println("firstRatingSource = " + firstRatingSource);

        //store the response in the Map
        // Since our Json object is in key and value format, we can directly store it into a Map
        // by giving empty string inside the map, we can store all data
       Map<String, Object> responseMap =  jp.getMap("");
        System.out.println("responseMap = " + responseMap);

        System.out.println(responseMap.get("Title"));
        
        Object awards = responseMap.get("Awards");
        System.out.println("Awards: "+awards);
        
        // One more example of Map 
        // Store First Rating Json Object into a map
        
       Map<String, Object> firstRating =  jp.getMap("Ratings[0]");
        System.out.println("firstRating = " + firstRating);


        // above code is doing below when getMap method is being called
//        Map<String, Object> manualMap = new HashMap<>();
//        manualMap.put("Source", "6.3/10");

        // I want to store all the Source of rating  into the list of String
        // my result should be [Internet Movie Database, Rotten Tomatoes, Metacritic ]
        // JSonPath  getList method will store items in jsonArray into the list
        
        // get me the list of Source field of the Ratings jsoarray the response
           List<String> sourceList = jp.getList("Ratings.Source");
        System.out.println("sourceList = " + sourceList);


    }






}
