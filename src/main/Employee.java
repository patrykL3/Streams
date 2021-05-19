package main;

import lombok.*;

import java.util.List;


/**
 * Create by Patryk Łubik on 19.05.2021.
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Employee {

    private String firstName;
    private String lastName;
    int age;

    private List<String> skills;

}
