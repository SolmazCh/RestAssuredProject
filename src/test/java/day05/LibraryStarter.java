package day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LibraryStarter {

    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
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
}
