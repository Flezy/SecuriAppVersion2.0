package com.example.levi.securiapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Levi on 2015.07.14..
 */
public class ViewPoint implements Serializable {

    private String description;
    private int score;
    private boolean critical;

    public ViewPoint(String description, boolean critical) {
        this.description = description;
        this.critical = critical;
        this.score = 0;
    }


    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public boolean isCritical() {
        return critical;
    }


}
