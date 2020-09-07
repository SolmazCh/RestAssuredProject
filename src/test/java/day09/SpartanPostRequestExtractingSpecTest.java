package day09;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.specification.ProxySpecification.auth;
import static org.hamcrest.Matchers.*;


public class SpartanPostRequestExtractingSpecTest {

    static ResponseSpecification responseSpec;
    static RequestSpecification requestSpec;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

        // preparing the body for request spec
        Spartan spartan = createRandomSpartanObject();

        requestSpec = given()
                              .auth().basic("admin", "admin")
                              .log().all()
                              .accept(ContentType.JSON)
                              .contentType(ContentType.JSON)
                               .body(spartan);

        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        responseSpec = resSpecBuilder
                                          .log(LogDetail.ALL)
                                          .expectStatusCode(201)
                                          .expectHeader("Date", notNullValue(String.class))
                                           .expectBody("success", is( "A Spartan is Born!"))
                                            .expectBody("data.name", is(spartan.getName()))
                                             .expectBody("data.gender", is(spartan.getGender()))
                                            .expectBody("data.phone", is(spartan.getPhone()))
                                              .expectBody("data.id", is(notNullValue()))
                                              .build();

     //   responseSpec = expect().contentType(ContentType.JSON).statusCode(200).logDetail(LogDetail.ALL) ;

      //  responseSpec = requestSpec.then().contentType(ContentType.JSON).statusCode(201);

    }


    @DisplayName("Extracting the requestSpec and responseSpec practice")
    @Test
    public void test(){

        // make a post request and assert the status code header and body
        // eventually extract out the spec for reuse

        Spartan randomSp = createRandomSpartanObject();

        // validPostRequestSpec
        // so we want to add the auth , contentType , randomBody , logging
        // into the request spec

        given()
                .spec(requestSpec).
         when()
                .post("/spartans").

        then()
                .spec(responseSpec);

    }







    public static Spartan createRandomSpartanObject(){
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        String randomGender = faker.demographic().sex();
        long randomNumber = faker.number().numberBetween(1000000000L, 9999999999L);

        Spartan spartan = new Spartan(randomName,randomGender,randomNumber);
        System.out.println("Created Random Spartan Object : " + spartan);
        return spartan;
    }
}
