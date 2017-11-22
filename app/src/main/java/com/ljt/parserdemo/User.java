package com.ljt.parserdemo;

public class User {
    //省略其它
    public String name;
    public int age;
    public String emailAddress;

    public User() {
    }

    public User(String laBi, int i) {
        this.name=laBi;
        this.age=i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}