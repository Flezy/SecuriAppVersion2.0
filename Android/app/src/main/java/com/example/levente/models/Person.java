package com.example.levente.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private static Person instance = null;
    int id;
    static Object lock = new Object();
    String post;
    String name, pictureSource;
    List<Rating> ratings;

    protected Person(){
        this.id = 213423;
        this.post = "helyszin";
        this.name = "Farkas Levente";
        this.ratings = new ArrayList<Rating>();
        /*TODO ezt kivenni miutan megvan az async*/
        initRatings();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public void setPost(String post){
        this.post = post;
    }

    public static Person getInstance(){
        if (instance == null){
            instance = new Person();
        }
        return instance;
    }

    /*
    TODO
        initRatings kesobb esemenyalapu lesz, az async request-response utána ha megvan a List<Object[]> vagy JSON
        akkor kap majd egy RatingListet amivel feltolti.
    */
    public void initRatings(){
        addRatingCollection(new Rating("Formaruha megléte" , true));
        addRatingCollection(new Rating("Igazolvány megléte" , true));
        addRatingCollection(new Rating("Tisztaság" , false));
        addRatingCollection(new Rating("most meg még valami" , false));

    }

    public List<Rating> getRatingList(){
        return ratings;
    }

    public void addRatingCollection(Rating r){
        this.ratings.add(r);
    }

    public String getPost() {
        return post;
    }
}