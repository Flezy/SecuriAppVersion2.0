package com.example.levente.models;

public class Rating {
    String text;
    int point;
    boolean critical;
    
    public Rating(String text, boolean critical){
        this.text = text;
        this.critical = critical;
    }

    public void setPoint(int point){
        this.point = point;
    }

    public int getPoint(){
        return point;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public boolean isCritical() {
        return critical;
    }
}
