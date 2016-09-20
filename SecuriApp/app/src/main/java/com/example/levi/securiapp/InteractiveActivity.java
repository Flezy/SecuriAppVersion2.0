package com.example.levi.securiapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;


public class InteractiveActivity extends ActionBarActivity {

    RadioButton rb1, rb2, rb3, rb4;
    TextView text2, text3;
    TextSwitcher textSwitcher;
    Button buttonNext, buttonBack;
    private int current = 0;
    Person currentPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive);
        currentPerson = new Person(getIntent().getStringExtra("name"));

        //#############################
        //Init Views
        //###############################

        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        text2 = (TextView) findViewById(R.id.textView2);           //the textview below rb2
        text3 = (TextView) findViewById(R.id.textView3);            //the textview  below rb3
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);     //description
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        //#################################
        //Animation of the description
        //#################################

        final Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(getApplicationContext());
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(25);
                myText.setTextColor(Color.BLACK);
                return myText;
            }
        });

        //######################################
        // onClickListener of buttonNext and buttonBack
        //#####################################

        //show first viewpoint
        textSwitcher.setText(currentPerson.getContainer().get(current).getDescription());


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current < currentPerson.getContainer().size()-1) {
                    current++;
                    update();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),ConclusionActivity.class);
                    intent.putExtra("person", currentPerson);
                    startActivity(intent);
                    finish();

                }
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (current > 0){
                    current--;
                    update();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    //#############################
    //RadioButton guard, only one can be checked
    //#############################
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

            switch(view.getId()) {
             	case R.id.radioButton1:
            	 	if (checked){
                        rb2.setChecked(false);
                        rb3.setChecked(false);
                        rb4.setChecked(false);
                        if (current >= 0)currentPerson.getContainer().get(current).setScore(1);
                        break;
                    }
                case R.id.radioButton2:
                    if (checked){
                     	rb1.setChecked(false);
       					rb3.setChecked(false);
                		rb4.setChecked(false);
                        if (current >= 0)currentPerson.getContainer().get(current).setScore(2);
                        break;
                	}
                case R.id.radioButton3:
                 	if (checked){
                	 	rb1.setChecked(false);
                		rb2.setChecked(false);
                		rb4.setChecked(false);
                        if (current >= 0)currentPerson.getContainer().get(current).setScore(3);
                        break;
                	}
                case R.id.radioButton4:
                 	if (checked){
                	 	rb1.setChecked(false);
                    	rb2.setChecked(false);
                    	rb3.setChecked(false);
                        if (current >= 0)currentPerson.getContainer().get(current).setScore(4);
                        break;
                    }
             }
       	}
    //The ViewPoints

    // UI Update

    private void update(){
        textSwitcher.setText(currentPerson.getContainer().get(current).getDescription());
        if (currentPerson.getContainer().get(current).isCritical()){
            hideRadiobuttons();
        }
        else{
            showRadiobuttons();
        }
        setScores();
    }

    private void showRadiobuttons() {
        rb2.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
    }

    private void hideRadiobuttons() {
        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
    }

    private void setScores(){
        switch (currentPerson.getContainer().get(current).getScore()){
            case 0:
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);
                break;
            case 1:
                rb1.setChecked(true);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);
                break;
            case 2:
                rb1.setChecked(false);
                rb2.setChecked(true);
                rb3.setChecked(false);
                rb4.setChecked(false);
                break;
            case 3:
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(true);
                rb4.setChecked(false);
                break;
            case 4:
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(true);
                break;
        }

    }

}
