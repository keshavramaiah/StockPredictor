package com.example.kuttr.stockpredictor;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends
        AppCompatActivity implements View.OnClickListener {
    EditText message;
    Button sendsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message = (EditText) findViewById(R.id.msg);
        sendsms = (Button) findViewById(R.id.sms);
        sendsms.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String no = "9080996617";
        String msg = message.getText().toString();
        //Getting intent and PendingIntent instance
        Intent intent=new Intent
                (getApplicationContext(),
                        settings.class);
        PendingIntent pi=PendingIntent.getActivity
                (getApplicationContext(),
                        0, intent,0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi, null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();

    }
}
