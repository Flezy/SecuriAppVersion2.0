package com.example.levi.securiapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levi on 2015.07.16..
 */
public class ViewPointContainer implements Serializable{
    private final List<ViewPoint> list = new ArrayList<ViewPoint>();

    public ViewPoint get(int i){return list.get(i);}
    public int size(){return list.size();}
    public void clear(){list.clear();}
    public void update(){
        list.add(new ViewPoint("Vagyonőri igazolvány megléte, érvényessége",true));
        list.add(new ViewPoint("Személyi azonosító igazolvány megléte",true));
        list.add(new ViewPoint("Securimaster azonosító igazolvány megléte",true));
        list.add(new ViewPoint("Formaruhán a Securimaster / biztonsági őr / felirat megléte",true));
        list.add(new ViewPoint("Formaruha előírtak szerinti viselet",false));

    }

}
