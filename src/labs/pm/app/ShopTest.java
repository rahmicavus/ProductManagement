package labs.pm.app;

import labs.pm.data.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.hamcrest.Matcher.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {

    private static Employee[] arrayOfEmps = {
            new Employee(1,"Jess Bezos",100),
            new Employee(2,"Bill Gates",85),
            new Employee(3,"Elon Musk",50)};
    private static List<Employee> empList = Arrays.asList(arrayOfEmps);
    private Employee employeeRepo;
    @Test
    public void testStream(){

        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        String matched = memberNames.stream()
                .filter(s -> s.startsWith("L"))
                .findFirst()
                .get();

        System.out.println(matched.toString());


    }
    }
