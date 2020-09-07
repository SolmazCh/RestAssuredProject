package day09;

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class SpartanRoleBaseASccessControlNegativeTest_Reuse {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData() {

        // building reusable request specification
        // using RequestSpecBuilder class

        RequestSpecification requestSpec = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON) // we are getting 403 with json body so accept header is json
                .log().all();


        // Extracting ResponseSpecification for all assertions so we can reuse
        // We will be using a class called ResponseSpecBuilder
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        // Getting the reusable ResponseSpecification object using the builder methods chaining
        ResponseSpecification responseSpec = responseSpecBuilder.expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("Date", notNullValue(String.class) )
                .log(LogDetail.ALL)
                .build();

        // expectHeader second argument expect a Matcher<String>
        // but notNullValue() return a Matcher<Object> so it did not compile
        // so we used the second version of notNullValue( The Matcher type inside <>)
        // to specify what kind of matcher we wanted


        given()
               .spec(requestSpec).
        when()
                .delete("/spartans/{id}", 38).

        then()
               .spec(responseSpec);
    }




    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData() {

        Spartan spartan = new Spartan("Clone", "Male", 8765431224L);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .auth().basic("user", "user")
                .body(spartan).
        when()
               .put("spartans/{id}", 43).

         then()
                .statusCode(403);
    }



    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData() {

        Spartan spartan = new Spartan("Clone", "Male", 8765431224L);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .auth().basic("user", "user")
                .body(spartan)
                .accept(ContentType.JSON).
                when()
                .post("spartans").

                then()
                .statusCode(403)
                .header("Date", is(notNullValue(String.class)) ); // checking Date header is not null
    }



    }
