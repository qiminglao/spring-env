package bean;

import lombok.Data;

@Data
public class Teacher {
    private String name;
    private String course;
    private Student student;
}
