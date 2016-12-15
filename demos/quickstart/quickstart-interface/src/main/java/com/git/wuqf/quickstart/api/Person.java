package com.git.wuqf.quickstart.api;

import java.io.Serializable;

/**
 * Created by sdzn-dsj on 2016/12/13.
 */
public class Person implements Serializable{
    private String name;
    private int age;

    public Person(String name, int age){
        this.name=name;
        this.age=age;
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

    @Override
    public String toString() {
        return "the age of  person "+name+" is "+age;
    }
}
