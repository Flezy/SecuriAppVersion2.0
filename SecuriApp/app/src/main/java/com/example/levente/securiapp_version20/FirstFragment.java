package com.example.levente.securiapp_version20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levente.barcode_util.*;
import com.example.levente.models.Person;

public class FirstFragment extends Fragment implements View.OnClickListener{

    private Button btnScan;
    private TextView nameText,identityText;
    private ImageView profilPic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.first_layout,
                container, false);
        btnScan = (Button)view.findViewById(R.id.buttonScan);
        btnScan.setOnClickListener(this);
        nameText = (TextView)view.findViewById(R.id.nameText);
        identityText = (TextView)view.findViewById(R.id.identityText);
        identityText.setVisibility(View.INVISIBLE);
        profilPic = (ImageView)view.findViewById(R.id.profilPic);

        return view;
    }
    public void onClick(View v) {
        if(v.getId()==R.id.buttonScan){
            ((MainActivity)getActivity()).scanBarcode();
        }

    }

    public void changeText(String result){
        String[] splitted = result.split("&");
        if (splitted.length > 1){
            nameText.setText(splitted[0]);
            identityText.setText(splitted[1]);
        }
        else {
            nameText.setText(splitted[0]);
            identityText.setText("default Identity");
        }
        Person createdNewPerson = new Person(Integer.parseInt(splitted[1]), splitted[0]);
        MainActivity ma = (MainActivity)getActivity();
        ma.setPerson(createdNewPerson);
        identityText.setVisibility(View.VISIBLE);
        btnScan.setVisibility(View.INVISIBLE);
        profilPic.setImageResource(R.drawable.revenant);
    }

}
