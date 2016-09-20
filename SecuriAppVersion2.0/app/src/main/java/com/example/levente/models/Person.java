package com.example.levente.models;

import java.util.List;

public class Person {
    int id;
    String name, pictureSource;
    List<Rating> ratings;

    public Person (int id, String name, String pictureSource){
        this.id = id;
        this.name = name;
        this.pictureSource = pictureSource;
    }

    public Person (int id, String name){
        this.id = id;
        this.name = name;
    }

    public List<Rating> getRatingList(){
        return ratings;
    }

    public void addRatingCollection(Rating r){
        this.ratings.add(r);
    }
}
