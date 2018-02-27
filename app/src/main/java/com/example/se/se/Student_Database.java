package com.example.se.se;

public class Student_Database {
    int id;
    String fname;
    String lname;
    int age;
    String school;

    public Student_Database() {}

    public Student_Database(String first_name, String last_name, int age, String school) {
        this.fname = first_name;
        this.lname = last_name;
        this.age = age;
        this.school = school;
    }

    public Student_Database(int id, String first_name, String last_name, int age, String school) {
        this.id = id;
        this.fname = first_name;
        this.lname = last_name;
        this.age = age;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}