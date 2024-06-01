package com.example;

public class Child {
    private String name;
    private String gender;
    private int age;
    private int number;
    private int groupNumber;

    public Child(int number, String name, String gender, int age, int groupNumber) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int number) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
