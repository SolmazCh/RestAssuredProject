package day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
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

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class practic {

    @Test
    public void test(){

        //GET https://api.got.show/api/book/characters
        //then store all the character name whose house value is House Stark
        //Hint :
        //The response is top level json array so you will not need to provide json path
        //The method will look like this getList(" findAll { condition here }.theFieldNameHere")
      Response response = given()
                .baseUri("https://api.got.show")
                .basePath("/api/book/").
         when()
                .get("/characters");

        JsonPath jp = response.jsonPath();

        response.prettyPeek();

        List<String> names = jp.getList("findAll{it.house == 'House Stark'}.name");

        System.out.println("names = " + names);

        assertThat(names ,hasSize(76));
        assertThat(names, hasItem("Eddard Stark"));
        assertThat(names, hasItems("Eyron Stark", "Farlen", "Gage"));


    }





}
