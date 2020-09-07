package day05;

import POJO.Spartan;
import POJO.Spartan2;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.port;

public class JsonToPOJO_Practice {


    //54.196.47.112
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://23.23.75.140";
        port =8000;
        RestAssured.basePath = "/api";
    }


    @DisplayName("Json to POJO GET/ spartans/{id}")
    @Test
    public void testSpartanJsonToSpartanObject(){

        int newId = SecureSpartanTest.createRandomSpartan();
   Response response =     given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id", newId).

        when()
                 .get("/spartans/{id}")
                .prettyPeek();

   //as method from response
        // accept a class type too define what is the type object you are trying to store the response as
        // Spartan2 class we created has all the fields that match the json fields from the response
        //So it will map all the json fields to the java fields and return Spartans POJO Object
        Spartan2 sp = response.as(Spartan2.class);
        //above line is almost as if you are doing below line
       // Spartan2 sp = new Spartan2(35, "Gardiner", "male", 3751113352L);
        System.out.println("sp = " + sp);

    }


    @DisplayName("Search Request and save 1st Result as Spartan2 POJO")
    @Test
    public void gettingNestedJsonAsPojo(){

                Response response =given()
                                .log().all().
                                 auth().basic("admin", "admin")
                                .queryParam("gender","Male").

                          when()

                                  .get("/spartans/search")
                                   .prettyPeek();


        System.out.println("response.statuscode: "+response.statusCode());
        //print out the first id and name from the response
        JsonPath jp = response.jsonPath();
        System.out.println("First ID: "+jp.getInt("content[0].id"));
        System.out.println(("First guy Name: "+jp.getString("content[0].name")) );



        //lets save the entire first json object in the array into Spartan2 POJO
        //getObject method accept 2 paramet
        // first param to rpovide the path to ypur json
        //second param to provide the type you want to store as Spartans2.class
        // evetually it will work just like new Spartan(id, name, male, phone)
        Spartan2 firstMaleSpartan = jp.getObject("content[0]", Spartan2.class);
        System.out.println("firstMaleSpartan= "+firstMaleSpartan);
        System.out.println("The spartan id from POSO is: "+firstMaleSpartan.getId());
        System.out.println("The spartan name from POSO is: "+firstMaleSpartan.getName());
        System.out.println("The spartan gender from POSO is: "+firstMaleSpartan.getGender());
        System.out.println("The spartan phone from POSO is: "+firstMaleSpartan.getPhone());


    }


    // I already know how to save 1 spartan json into Spartans2 POJO
    //How can I store the entire jsonArray into the list<Spartan2>

    @DisplayName("Save the json array as list<Spartans2>")
    @Test
    public void testJsonArrayToListOfPojo(){

        Response response =given()
                                  .auth().basic("admin", "admin")
                                  .queryParam("gender", "Female").

                           when()
                                  .get("/spartans/search");

        //store all ids as list of Integer
        JsonPath jp = response.jsonPath();
        System.out.println("List of id: "+jp.getList("content.id"));

        //all name
        System.out.println("List of Names: "+jp.getList("content.name"));


        //store the entire jsonArray as list of spartan2
       List<Spartan2> spartan2list = jp.getList("content", Spartan2.class);


        //System.out.println(spartan2list);


        // with for each loop just for printing list in orginized manner
        for( Spartan2 each : spartan2list){
            System.out.println(each);
        }

        //other way to print the list with organized manner with lambda expression
        spartan2list.forEach(each -> System.out.println(each) );

    }


}
