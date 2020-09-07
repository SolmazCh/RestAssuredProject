package day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class newsapi {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI= "http://newsapi.org";
        RestAssured.basePath="/v2";

    }


    @Test
    public void test(){
        String apiKey = "42bb42f550eb432a90d48201b33380e5";

     Response response= given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization", "Bearer 42bb42f550eb432a90d48201b33380e5").
        when()

                .get("/top-headlines?country=us");
      response.prettyPeek();
        JsonPath jp = response.jsonPath();

        List<String> authorNames = jp.getList("articles.findAll{it.author != null}.author");

        System.out.println("authorNames = " + authorNames);




    }





}
