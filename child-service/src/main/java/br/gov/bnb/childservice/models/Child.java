package br.gov.bnb.childservice.models;

public class Child {
    private final int id;
    private final String name;
    private final int age;

    public Child(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
