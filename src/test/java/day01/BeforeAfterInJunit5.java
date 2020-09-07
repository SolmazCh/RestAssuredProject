package day01;

import org.junit.jupiter.api.*;

public class BeforeAfterInJunit5 {


    @BeforeAll  // this run before the entire test. has to be static
    public static void setUp(){
        System.out.println("This run before All");
    }

    @BeforeEach
    public void beforeEachTest(){
        System.out.println("Running before the test");
    }


   @Disabled // same with @Ignore in TestNG
    @Test
    public void test1(){
        System.out.println("Test is running");
    }


    @AfterEach
    public void afterEachMethod(){
        System.out.println("After each running");
    }

    //this will run after all test only once
    //same as AfterClass annotation learned previously
    @AfterAll
    public static void tearDown(){
        System.out.println("This run all the way at the end");
    }


}
