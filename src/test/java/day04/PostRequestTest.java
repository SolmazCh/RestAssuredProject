package day04;

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
import static org.hamcrest.Matchers.is;
public class PostRequestTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }


 @DisplayName("Post request with String body")
    @Test
    public void testPostWithStringBody(){
        // lets try to get random name
     Faker faker  = new Faker();
     String randomName =  faker.name().firstName();

// try to randomize gender and phone num on your own at home
     String bodyString = "{\n" +
             "  \"name\"  : \"  " + randomName+ "     \",\n" +
             "  \"gender\": \"Female\",\n" +
             "  \"phone\": 6234567890\n" +
             "}";
     given()
             .log().all()
             .contentType(ContentType.JSON)
             .body(bodyString).
             when()
             .post("/spartans").
             then()
             .log().all()
             .statusCode(201);

 }


@DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile(){

        //create a file object that point to spartan.json yo just added
    // so we can use this as body in post request
        File fil1 = new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(fil1).

         when()
                .post("/spartans").
         then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("Venera"));

}

   @DisplayName("Posting with map object as body")
    @Test
    public void testPostWithMapAsBody(){
        // add dependency jackson-databind

       // create a Map<String, Object> as hashMap
       //add name, gender , phone

       Map<String, Object> bodyMap = new LinkedHashMap<>();
       bodyMap.put("name", "Victor");
       bodyMap.put("gender", "Male");
       bodyMap.put("phone", 32165487690L);

       System.out.println("bodyMap = " + bodyMap);



       given()
               .contentType(ContentType.JSON)
               .body(bodyMap)
               .log().all().

       when()

                .post("/spartans").
        then()
                 .log().all()
                  .statusCode(201)
               .body("data.name", is("Victor"));






   }






}
