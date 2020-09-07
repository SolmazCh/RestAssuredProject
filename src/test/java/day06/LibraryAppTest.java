package day06;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
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
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {
    public static String loginAndGetToken(String username, String password){
        String token = "";
        Response response = given()
//                                .log().all()
                // explicitly saying the body content type is x-www-urlencoded-form-data
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password", password ).
                        when()
                .post("/login") ;
        //token = response.path("token") ;  // this is using path method
        token = response.jsonPath().getString("token") ;
        return token ;
    }

    private static  String libraryToken;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        basePath="/rest/v1" ;
        libraryToken =loginAndGetToken("librarian69@library","KNPXrm3S" );
    }

@DisplayName("Send request to /dashboard_stats")
    @Test
    public void testDashboardWithToken(){

        given()
                .log().all()
                .header("x-library-token", libraryToken)

        .when()
                 .get("/dashboard_stats")
        .then()
                .log().all()
                 .statusCode(200)
                .body("borrowed_books", is("600"));

}

//add a test for the POST /decode endpoint
// this endpoint does not need authorization
// it accept form param as name token value your long token
// and return json response as user information and authority
// assert the email of user is same as the email you used the token

@DisplayName("Testing post/decode endpoint")
    @Test
    public void testDekodJWT(){

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .log().all()
                .formParam("token", libraryToken).

         when()
               .post("/decode").
        then()
               .statusCode(200)
                .body("token", is(libraryToken));

        //406  error -> not accesable --> meselen accept header json verirsen amma o
        // ancaq txt qebul edirse onda 406 error verecek
}



    @DisplayName("test/ get_user_by_id/{id} enpoint")
    @Test
    public void testingSingleUserDate(){

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 2080)
                .header("x-library-token",libraryToken)
                .log().all().


                when()
                .get("get_user_by_id/{id}")
                .prettyPeek().

                then()
                .statusCode(200)
                .body("id", is("2080"))
                .body("password",is("830f1d9884d7005b9b910148aa73da96"))
                .body("image", is(notNullValue()));



    }


}
