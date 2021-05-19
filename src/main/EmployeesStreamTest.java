package main;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by Patryk Łubik on 19.05.2021.
 */


public class EmployeesStreamTest {

    List<Employee> employees = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        Employee emp1 = new Employee("A", "AA", 1, List.of("as1", "as2", "as3"));
        Employee emp2 = new Employee("B", "BB", 1, List.of("bs1", "bs2", "bs3"));
        Employee emp3 = new Employee("C", "CC", 1, List.of("cvs1", "cs2", "cs3"));
        Employee emp4 = new Employee("D", "DD", 1, List.of("ds1", "sd2", "ds3"));
        Employee emp5 = new Employee("E", "EE", 1, List.of("es1", "es2", "es3"));

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
    }


    @Test
    public void firstStream() {

        employees.stream()
            .forEach(employee -> System.out.println(employee));
    }

}
