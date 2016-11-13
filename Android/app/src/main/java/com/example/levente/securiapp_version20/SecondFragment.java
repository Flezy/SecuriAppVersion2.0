package com.example.levente.securiapp_version20;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.levente.models.Person;


public class SecondFragment extends Fragment implements View.OnClickListener {

    LinearLayout content;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.second_layout, container, false);

        createRatingListView();
        return view;
    }
    public void createRatingListView(){

        try {

            DisplayMetrics displaymetrics = new DisplayMetrics();
            ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                    .getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;


            content = (LinearLayout)view.findViewById(R.id.contentLinLay);


            for (int i = 0; i < Person.getInstance().getRatingList().size(); ++i) {

                // LinearLayout create

                LinearLayout ll1 = new LinearLayout(view.getContext());
                ll1.setLayoutParams(
                        new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));


                ll1.setOrientation(LinearLayout.VERTICAL);
                ll1.setGravity(View.TEXT_ALIGNMENT_CENTER);

                // Text of Rating

                TextView tv1 = new TextView(new ContextThemeWrapper(view.getContext(), R.style.textOfRatings));
                tv1.setText(Person.getInstance().getRatingList().get(i).getText());
                tv1.setGravity(View.TEXT_ALIGNMENT_CENTER);

                // RadioGroup

                RadioGroup rgroup = new RadioGroup(view.getContext());
                rgroup.setOrientation(LinearLayout.HORIZONTAL);
                addRadioButton(rgroup, i);
                ll1.addView(tv1);
                ll1.addView(rgroup);
                content.addView(ll1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addRadioButton(RadioGroup rgroup, int ratingIndex) {
        if (Person.getInstance().getRatingList().get(ratingIndex).isCritical()){
            add2Buttons(rgroup, ratingIndex);
        }
        else{
            add4Buttons(rgroup, ratingIndex);
        }
    }

    private void add2Buttons(RadioGroup rgroup, int ratingIndex) {
        RadioButton radioButton;
        for (int i = 0; i < 2; ++i){
            radioButton = new RadioButton(getContext());
            radioButton.setOnClickListener(this);
            rgroup.addView(radioButton);
        }
    }
    private void add4Buttons(RadioGroup rgroup, int ratingIndex){
        RadioButton radioButton;
        for (int i = 0; i < 4; ++i){
            radioButton = new RadioButton(getContext());
            radioButton.setOnClickListener(this);
            rgroup.addView(radioButton);
        }
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i<content.getChildCount(); ++i){
            View v1 = content.getChildAt(i);
            LinearLayout tmpLay= (LinearLayout) v1;
            // getting 2. element of the LinearLayout
            // TODO structure
            RadioGroup rgrp = (RadioGroup)tmpLay.getChildAt(1);
            int radioButtonID = rgrp.getCheckedRadioButtonId();
            View radioButton = rgrp.findViewById(radioButtonID);
            int idx = rgrp.indexOfChild(radioButton);
            // idx + 1 because idx starts with 0, but we dont have 0 point, we start with 1.
            Person.getInstance().getRatingList().get(i).setPoint(idx + 1);
        }

        MainActivity ma = (MainActivity)getActivity();
        ma.refreshPointsOnThirdActivity();
    }
}
