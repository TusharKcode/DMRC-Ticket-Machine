package org.example;

public class Station {
    private final int id;
    private final String name;
    private final String lineColor;
    private double distanceFromOrigin;


    public Station(int id, String name, String lineColor, double distanceFromOrigin){
        this.id = id;
        this.name = name;
        this.lineColor = lineColor;
        this.distanceFromOrigin = distanceFromOrigin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return lineColor;
    }

    @Override
    public String toString(){
        return name;
    }

    public double getDistanceFromOrigin(){
        return distanceFromOrigin;
    }
}
