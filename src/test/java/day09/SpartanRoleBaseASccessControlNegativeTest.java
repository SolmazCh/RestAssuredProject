package day09;

import POJO.Spartan;
import com.google.protobuf.NullValue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBaseASccessControlNegativeTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData() {

        given()
                .auth().basic("user", "user")
                .contentType(ContentType.JSON)
                .log().all().
        when()
                .delete("/spartans/{id}", 38).

        then()
                .statusCode(403)
                .log().all();
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
                .header("Date", is(notNullValue()) ); // checking Date header is not null
    }



    }
