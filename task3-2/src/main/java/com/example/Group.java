package com.example;

import java.util.List;

public class Group {
    private String name;
    private int number;
    private List<Child> children;

    public Group(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public void removeChild(Child child) {
        children.remove(child);
    }
}
