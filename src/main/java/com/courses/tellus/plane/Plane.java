package com.courses.tellus.plane;

public class Plane {
    private final String name;
    private final int capacity;
    //private Scanner reader;

    public Plane(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.capacity + " ppl)";
    }
}
