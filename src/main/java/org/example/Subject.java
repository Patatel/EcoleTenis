package org.example;

public class Subject {
    int id;
    String name;
    int factor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public Subject(int id, String name, int factor) {
        this.id = id;
        this.name = name;
        this.factor = factor;
    }
}
