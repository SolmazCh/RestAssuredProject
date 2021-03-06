package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SampleTest {

    @Test
    public void calculatorTest(){

        System.out.println("Hello World");

        // assert 4+5 is 9
        assertEquals(9, 4+5);

        // how do we add error message if the assertion fail
       // assertEquals(10, 5+4, "Hey wrong result!!");
    }


    //you can add the display name for your test instead of the method name
    // providing the custom name for the test
    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){

        //write a simple assertion
        //concatenate first name and last name
        // and make assertion it's equal to your full name

        String firstName = "Solmaz";
        String lastName = "Chiragova";
        String fullName = "Solmaz Chiragova";

        assertEquals(fullName, firstName+" "+lastName);


    }

}
