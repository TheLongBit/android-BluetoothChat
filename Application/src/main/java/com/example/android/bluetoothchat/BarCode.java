package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarCode extends Activity implements View.OnClickListener {
    private Button scanBtn;
    private TextView formatTxt, contentTxt, SendBar;
    public String Driver_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_code_layout);
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        SendBar = (TextView) findViewById(R.id.edit_text_out);
        scanBtn.setOnClickListener(BarCode.this);



    }
    public void onClick(View v){

        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            SendBar.setText(scanContent);
            contentTxt.setText("CONTENT: " + scanContent);
            Driver_ID = scanContent;
            SendBar.setEnabled(false);
            Intent chatintent = new Intent(this, MainActivity.class);
            startActivity(chatintent);




        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

