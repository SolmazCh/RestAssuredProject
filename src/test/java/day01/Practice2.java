package day01;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

//Hamcrest library is an assertion library
// to aim make the test more human readable
// using lot of human readable matchers like something is(something else)
//Most importantly restAssured use hamcrest matcher
//when we chain multiple  rest assured methods
public class Practice2 {


    @DisplayName("test equals using hamcrest matchers")
    @Test
    public void test1(){

        //assert 5+4 is 9
        int num1 = 5;
        int num2 =4;

        // Hamcrest already come with RestAssured dependency
        //we need these two import for this to work
        // hamcrest library use the assertThat method for all assertions
        //hamcrest use built in matchers to do assertions
        //couple common ones are:
        // is(some value)
        //equalTo(value)
        // or optionally is ( equalTo(some value) )

        assertThat(num1+num2, is(9) );
        assertThat(num1+num2, equalTo(9) );
        assertThat(num1+num2, is(equalTo(9) ) );

        //not(value)
        // is(not(some value))
        // not(equalTo(11))
        assertThat(num1+num2, not(11));
        assertThat(num1+num2, is(not(11)));


        //save your first name and last name into 2 variables
        //test the concat result using hamcrest matcher

        String firstName = "Solmaz ";
        String lastName = "Chiragova";

        assertThat(firstName+lastName, is("Solmaz Chiragova"));
        assertThat(firstName+lastName, equalTo("Solmaz Chiragova"));
        assertThat(firstName+lastName, is(equalTo("Solmaz Chiragova")));

        //how to check in caseInsensitive manner
        assertThat(firstName, equalToIgnoringCase("solmaz "));

        // how to ignore all whitespaces
        assertThat(firstName, equalToCompressingWhiteSpace("Solmaz"));

    }

    @DisplayName("Support for Collection")
    @Test
    public void test2(){
        List<Integer> numList = Arrays.asList(11,44,3,55,88,5);
        //checking list size is 6
        assertThat(numList, hasSize(6));
        assertThat(numList.size(), is(6));
        //checking list contains 11
        assertThat(numList, hasItem(11));
        // checking list contains more than 1 item in order for exam: 11,44,3
       assertThat(numList, contains(11,44,3,55,88,5));

        // checking list contains more than 1 items in any order: 11,44,55
        assertThat(numList, containsInAnyOrder(11,44,55,3,88,5));


    }
}












