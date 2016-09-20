package com.example.levi.securiapp;

import java.io.Serializable;


public class Person implements Serializable {
    private String name;
    private ViewPointContainer points = new ViewPointContainer();

    public Person (String name){
        this.name = name;
        points.update();
    }
    public ViewPointContainer getContainer(){
        return points;
    }
    public String getName(){
        return name;
    }
}
