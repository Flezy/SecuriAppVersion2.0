package com.example.levi.securiapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import static android.view.ViewGroup.*;


public class ConclusionActivity extends ActionBarActivity {
    Button buttonemail;
    Person person;
    TextView textName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);
        textName = (TextView)findViewById(R.id.textName);
        person = (Person) getIntent().getExtras().getSerializable("person");
        textName.setText(person.getName());
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutofTexts);
        LinearLayout linearLayoutLittle = new LinearLayout(getApplicationContext());
        linearLayoutLittle.setOrientation(LinearLayout.VERTICAL);
        linearLayoutLittle.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < person.getContainer().size(); i++){
            final TextView textViewT1 = new TextView(getApplicationContext());
            linearLayoutLittle.addView(setTextView(textViewT1,i));
        }
        linearLayout.addView(linearLayoutLittle);

    buttonemail = (Button)findViewById(R.id.buttonSend);
    buttonemail.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"levnzz@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
            i.putExtra(Intent.EXTRA_TEXT   , "body of email");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }

    private TextView setTextView(TextView textView, int index) {
        textView.setText(person.getContainer().get(index).getDescription() + ": " + person.getContainer().get(index).getScore());
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        return textView;
    }


}

