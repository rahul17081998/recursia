package com.demo.streamExample;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter
public class CollegeStudent {
    private String name;
    private int age;
    private double gpa;
    private List<String> subjects;

    public CollegeStudent(String name, int age, double gpa, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.subjects = subjects;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    public List<String> getSubjects() { return subjects; }

    @Override
    public String toString() {
        return name + " - " + age + " - " + gpa + " - " + subjects;
    }
}