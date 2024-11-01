package com.example.studentinfoapp;

public class StudentInfo {
    private String name;
    private int age;
    private int grade;
    private String major;

    public StudentInfo(String name, int age, int grade, String major) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getGrade() { return grade; }
    public String getMajor() { return major; }
}