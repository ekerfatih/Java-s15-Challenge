package org.workintech.challange.models;

public abstract class Person {
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void whoYouAre() {
        System.out.println("My name is " + name);
    }

    public String getName() {
        return name;
    }
}

