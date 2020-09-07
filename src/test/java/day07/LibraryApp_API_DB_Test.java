package day07;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;
import utility.LibraryUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Collections;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class LibraryApp_API_DB_Test {

private static String libraryToken;
    @BeforeAll
    public static void setUp(){
        //libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnv("library2");
        String active_env = ConfigurationReader.getProperty("active_env");
        libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnv(active_env) ;
    }

    
    @Test
    public void test(){
        System.out.println("libraryToken = " + libraryToken);
        DB_Utility.runQuery("SELECT count(*) from books"); // it return the book count as single row and col
       String bookCount = DB_Utility.getColumnDataAtRow(1,1);

        DB_Utility.runQuery("SELECT count(*) from users"); // it return the user count as single row and col
        String userCount = DB_Utility.getColumnDataAtRow(1,1);

        DB_Utility.runQuery("SELECT count(*) from book_borrow where is_returned=false"); // it return the book count as single row and col
        String borrowedBookCount = DB_Utility.getColumnDataAtRow(1,1);
        System.out.println("borrowedBookCount = " + borrowedBookCount);
        System.out.println("userCount = " + userCount);
        System.out.println("bookCount = " + bookCount);

        given()
                .log().all()
                .header("x-library-token", libraryToken).
         when()
                 .get("/dashboard_stats")
                .prettyPeek().

         then()
                 .body( "book_count",is(bookCount))
                .body("borrowed_books",is(borrowedBookCount))
                .body("users",is(userCount))
                ;

    }
    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
        RestAssured.reset(); // this is for resetting all the values we set for restAssured itself



    }




}






