package day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class Practice1 {




    @DisplayName("Get All Characters Simple Test")
    @Test
    public void testBreakingBad(){
        when()
                .get("https://www.breakingbadapi.com/api/characters").
                prettyPeek().
         then().
                statusCode( is(200))      // checking status code
                .header("Content-Type", is("application/json; charset=utf-8"));
    }
}
