package day03;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class SpartanSearchExtractData {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }

    //		Add all the imports
    //		add @BeforeAll to initilize the baseURI, basePath
    //		Add a test
    //		send get request to /spartan/search
    //		query paramters gender = Female
    //		save the response Object into response variable
    //		call jsonPath method to get JsonPath object from respone
    //		JsonPath jp = response.jsonPath() ;
    //		// get the list of all IDs store it into List
    //		// get the list of all names store it into List of String
    //		// get the mumberOfElements field value

    @DisplayName("Search spartan")
    @Test
    public void searchtest(){

      Response response =  given()
                .log().all()
                .queryParam("gender", "Male").

        when()
                .get("spartans/search");



      response.prettyPrint();
      JsonPath jp = response.jsonPath();

            // get the list of all IDs store it into List
        	// get the list of all names store it into List of String
        	// get the mumberOfElements field value

        System.out.println("=========================================");
        List<Integer> idList = jp.getList("content.id");
        System.out.println("idList = " + idList);

        System.out.println("==============================");
        
        List<String>  nameList = jp.getList("content.name");
        System.out.println(nameList);

        int numberOfElement = jp.getInt("numberOfElements");
        System.out.println("numberOfElement = " + numberOfElement);

        System.out.println("=============================");
       List<Integer> phoneList =  jp.getList("content.phone");
        System.out.println("phoneList = " + phoneList);

        // if you want to get single spartan, for example the first one id
        // you would use jsonPath of content[0].id
        // if you want to get all the ids, you can use getlist method and remove the index
        // content.id for the id, content.name for name ....

    }


}
