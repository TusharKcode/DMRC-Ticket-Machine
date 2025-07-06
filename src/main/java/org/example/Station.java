package org.example;

public class Station {
    private final int id;
    private final String name;
    private final String line;
    private final String color;

    public Station(int id, String name, String line, String color){
        this.id = id;
        this.name = name;
        this.line = line;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString(){
        return name + " ( " + line + " ) ";
    }
}
