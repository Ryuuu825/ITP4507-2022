package com.BMS.Model;

public class Room {
    private double length;
    private double width;

    public Room(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String toString() {
        // Room No.: 1, Length: 15.0, Width: 20.0
        return "Length: " + getLength() + ", Width: " + getWidth();
    }
}
