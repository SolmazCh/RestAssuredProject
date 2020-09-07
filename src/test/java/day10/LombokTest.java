package day10;

import POJO.Category;
import POJO.Countries;
import org.junit.jupiter.api.Test;

public class LombokTest {

    @Test
    public void Test(){
        Category c1 = new Category("12", "abc");
        System.out.println("c1 = " + c1);


        Countries ar = new Countries("AR", "Argentina", 4);
        System.out.println("ar = " + ar);
    }






}
