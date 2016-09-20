package com.example.levi.securiapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class BarcodeActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnContinue;
    private Button btnScan;
    private TextView testText1, testText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        btnContinue = (Button)findViewById(R.id.buttonContinue);
        btnScan = (Button)findViewById(R.id.buttonScan);
        btnScan.setOnClickListener(this);
        testText1 = (TextView)findViewById(R.id.testText1);
        testText2 = (TextView)findViewById(R.id.testText2);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InteractiveActivity.class);
                intent.putExtra("name","valami");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonScan){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scnResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scnResult != null) {
            String scnCnt = scnResult.getContents();
            String scnFormat = scnResult.getFormatName();
            testText1.setText("FORMAT: " + scnFormat);
            testText2.setText("CONTENT: " + scnCnt);
            Intent intent2 = new Intent(getApplicationContext(),InteractiveActivity.class);
            intent2.putExtra("name",scnCnt);
            startActivity(intent2);
            finish();

        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Nincs scannelt adat vagy a művelet meg lett szakítva!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
