package day08;

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E_HappyPath {
    // We want exact order 1.Add , 2, Read, 3 Update , 4 Delete
    // we want the id that generated from post request accessible for all the tests
    static int newID ;
    Response response;


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }

    @Order(1)
    @Test
    public void testAddData(){
        // create one data here using the POJO as body, do your assertion
        // and grab the id so it can be used for all next 3 tests
        System.out.println("New ID is generated from the post request and saved " );

        Spartan spartan = new Spartan("Davud", "Male", 1234567891);
        response = given()
                .contentType(ContentType.JSON)
                .body(spartan)
                .log().all().
        when()
                 .post("/spartans")
                 .prettyPeek();

       newID=response.path("data.id");
      assertThat(response.statusCode(), is(201));
      assertThat(response.path("data.id"), is(newID));


    }
    @Order(2)
    @Test
    public void testReadData(){
        // use the ID that have been generated from previous request
        // assert the response json according to expected data
        // expected data is the same data you used to create in previous request
        // you can make your post body from previous request at class level
        // so it can be accessible here
        // or you can also query the DB to get your expected data
        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for get Request ");


        when()
                .get("/spartans/{id}", newID).

        then()
                .statusCode(200)
                .body("name", is("Davud"));


    }

    @Order(3)
    @Test
    public void testUpdateData(){
        Spartan spartan = new Spartan("Tural", "Male", 2143658709);

        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for PUT Request ");

        given()
                .contentType(ContentType.JSON)
                .body(spartan).

        when()
                .patch("/spartans/{id}", newID).

        then()
                .statusCode(204);
    }

    @Order(4)
    @Test
    public void testDeleteData(){
        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for DELETE Request ");

        when()
                .delete("/spartans/{id}", newID).
       then()
                .statusCode(204);
    }



}
