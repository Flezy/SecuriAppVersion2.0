package com.example.levi.securiapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView securiText;
    Button newButton, loadButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        securiText = (TextView) findViewById(R.id.SecuriMaster);
        newButton = (Button) findViewById(R.id.newButton);
        loadButton = (Button) findViewById(R.id.loadButton);
        exitButton = (Button) findViewById(R.id.exitButton);

        String styledSecuri = "<p align= 'center'><b>Securi<font color = 'red'>Master</font></b></p>";
        securiText.setText(Html.fromHtml(styledSecuri),TextView.BufferType.SPANNABLE);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BarcodeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoadActivity.class);
                startActivity(intent);
                finish();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}




