package com.example.levente.securiapp_version20;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.levente.models.Person;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ThirdFragment extends Fragment {

    /**
     * Font variables that we change as we wish
     * Its needed to use the same Design architecture
     */
    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    LinearLayout content;
    Button senderButton;
    View view;
    TextView textOfThirdLayoutIdTitle;
    TextView textOfThirdLayoutNameTitle;
    TextView textOfThirdLayoutPostTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.third_layout, container, false);
        content = (LinearLayout)view.findViewById(R.id.thirdLayoutContent);
        senderButton = (Button)view.findViewById(R.id.senderButton);
        textOfThirdLayoutIdTitle = (TextView)view.findViewById(R.id.thirdLayoutIdTitle);
        textOfThirdLayoutNameTitle = (TextView)view.findViewById(R.id.thirdLayoutNameTitle);
        textOfThirdLayoutPostTitle = (TextView)view.findViewById(R.id.thirdLayoutPostTitle);

        refreshPersonalityTextThirdFragment();
        fillUpContent();


        return view;
    }

    public void fillUpContent() {
        content.removeAllViews();
        for (int i = 0; i < Person.getInstance().getRatingList().size(); ++i){

            //create new LinearLayout

            LinearLayout ll3 = new LinearLayout(getContext());
            ll3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ll3.setOrientation(LinearLayout.HORIZONTAL);

            // Text of Rating

            TextView tv1 = new TextView(new ContextThemeWrapper(view.getContext(), R.style.textOfRatings));
            tv1.setText(Person.getInstance().getRatingList().get(i).getText());

            // RatingPoint calc

            int ratingPoint = getRaitingPoint(i);
            TextView tv2 = new TextView(new ContextThemeWrapper(view.getContext(), R.style.textOfBigPoints));
            tv2.setText(String.valueOf(ratingPoint));

            // addViews

            ll3.addView(tv1);
            ll3.addView(tv2);
            content.addView(ll3);
        }
    }
    public void refreshPersonalityTextThirdFragment(){
        textOfThirdLayoutIdTitle.setText(String.valueOf(Person.getInstance().getId()));
        textOfThirdLayoutNameTitle.setText(Person.getInstance().getName());
        textOfThirdLayoutPostTitle.setText(Person.getInstance().getPost());
    }
    private int getRaitingPoint(int i) {
        return Person.getInstance().getRatingList().get(i).getPoint();
    }


}
