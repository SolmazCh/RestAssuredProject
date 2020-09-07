package day08;

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class spartanPostParametir {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.159.3.135:8000";
        RestAssured.basePath = "/api";
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/allSpartans.csv", numLinesToSkip = 1)
    public void postRequestWithData(String name, String gender, long phone){

//        Map<String, Object> bodyMap = new LinkedHashMap<>();
//       bodyMap.put("name", name);
//      bodyMap.put("gender", gender);
//       bodyMap.put("phone", phone);

       Spartan spartan = new Spartan(name,gender,phone);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).


                when()
                .post("/spartans")
                .prettyPeek().
                then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is(name))
                .body("data.gender", is(gender))
                .body("data.id", is(not(0)));
    }

}
