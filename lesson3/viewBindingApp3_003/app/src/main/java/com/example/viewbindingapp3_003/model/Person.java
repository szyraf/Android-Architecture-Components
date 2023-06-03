package com.example.viewbindingapp3_003.model;

public class Person {
    private String sex;
    private String name;

    public Person(String sex, String name) {
        this.sex = sex;
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}