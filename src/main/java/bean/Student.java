package bean;

import lombok.Data;

@Data
public class Student {
    private String name;
    private int age;
    private Teacher teacher;
}
