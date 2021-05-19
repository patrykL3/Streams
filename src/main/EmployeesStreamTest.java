package main;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Create by Patryk Łubik on 19.05.2021.
 */


public class EmployeesStreamTest {

    List<Employee> employees = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        Employee emp1 = new Employee("A", "AA", 1, List.of("s1", "as2", "as3"));
        Employee emp2 = new Employee("B", "BB", 1, List.of("s1", "bs2", "bs3"));
        Employee emp3 = new Employee("C", "CC", 1, List.of("s1", "cs2", "cs3"));
        Employee emp4 = new Employee("D", "DD", 1, List.of("s1", "sd2", "ds3"));
        Employee emp5 = new Employee("E", "EE", 31, List.of("s1", "es2", "es3"));

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
    }


    @Test
    public void firstStream() {
        employees
            .forEach(System.out::println);
    }

    @Test
    public void mapOperation() {
        employees.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .forEach(System.out::println);
    }

    @Test
    public void flatMapOperation() {
        List<List<String>> allSkills = employees.stream()
                .map(employee -> employee.getSkills())
                .collect(Collectors.toList());

        System.out.println(allSkills);

        List<String> allSkills2 = employees.stream()
                .map(Employee::getSkills)
                .flatMap(Collection::stream)
                .distinct() // usuwanie powtarzajacych sie wartosci
                .collect(Collectors.toList());

        System.out.println(allSkills2);
    }

    @Test
    public void filterOperation() {
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("A"))
                .forEach(System.out::println);
    }

    @Test
    public void sortedOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .forEach(System.out::println);
    }

    @Test
    public void limitOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void skipOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void countOperation() {
        long numberOfEmployees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .count();

        System.out.println(numberOfEmployees);
    }

    @Test
    public void minMaxOperation() {
        Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge)).get();

        Employee oldestEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getAge)).get();

        System.out.println(youngestEmployee);
        System.out.println(oldestEmployee);
    }

    @Test
    public void findAnyFindFirstOperation() {
        Employee firstBEmployee = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .findFirst().get();

        Employee anyBEmployee = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .findAny().get();

    }

    @Test
    public void matchOperation() {
        boolean b1 = employees.stream()
                .allMatch(employee -> employee.getFirstName().startsWith("B"));

        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch(employee -> employee.getFirstName().contains("B"));

        System.out.println(b2);

        boolean b3 = employees.stream()
                .noneMatch(employee -> employee.getFirstName().startsWith("Z"));

        System.out.println(b3);
    }

    @Test
    public void reduceOperation() {
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
//                .reduce((age1, age2) -> age1 + age2).get();
                .reduce(Integer::sum)
                .get();

        System.out.println(sumOfAllAges);

        Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0, Integer::sum);

        System.out.println(sumOfAllAges2);


        Integer sumOfAllAges3 = employees.stream()
                .reduce(0, (age, employee) -> age + employee.getAge(), Integer::sum);

        System.out.println(sumOfAllAges3);

        String allNames = employees.stream()
                .map(Employee::getFirstName)
                .reduce((name, name2) -> name + ", " + name2)
                .get();

        System.out.println(allNames);

    }

    @Test
    public void takeWhileOperation() { // procesuje elementy dopoki spelniony jest warunek
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileOperation() { // pomija elementy dopoki spelniony jest predykat
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void foreachOrderOperation() {
        String sentence = "Cwiczenie programowania.";
        sentence.chars().forEach(s -> System.out.print((char)s));
        sentence.chars().parallel().forEach(s -> System.out.print((char)s)); // parallel- wielowątkowo
        sentence.chars().parallel().forEachOrdered(s -> System.out.print((char)s));
    }

    @Test
    public void peekOperation() { // tylko do podgladania strema podczas debugowania
        List<Employee> newEmployees = employees.stream()
                .peek(employee -> employee.setFirstName("peekName"))
                .collect(Collectors.toList());

        System.out.println(newEmployees);
        System.out.println();
        System.out.println(employees);
    }

}
