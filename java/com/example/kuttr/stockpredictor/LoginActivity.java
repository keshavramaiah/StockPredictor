package com.example.kuttr.stockpredictor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class LoginActivity extends AppCompatActivity implements OnClickListener {
    EditText username, password;
    SQLiteDatabase db;
    Button signin;
    CheckBox checkbox;
    TextView link_signup;
    private static final int REQUEST_SIGNUP = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = openOrCreateDatabase
                ("SPLogin", Context.MODE_PRIVATE, null);
        db.execSQL
                ("CREATE TABLE IF NOT EXISTS " +
                        "login(login_id VARCHAR PRIMARY KEY," +
                        "name VARCHAR,"+"email_id VARCHAR,"+"phone_no Number(10),"+"password VARCHAR);");


        checkbox=findViewById(R.id.cb);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        link_signup=findViewById(R.id.link_signup);


        signin = findViewById(R.id.signin_button);
        signin.setOnClickListener(this);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        link_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        Fabric.with(this, new Crashlytics());

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == signin.getId()) {
            if (TextUtils.isEmpty(username.getText())) {
                username.setError("You must enter a valid username");
            }
            if (TextUtils.isEmpty(password.getText()) || password.getText().toString().length() < 4) {

                password.setError("You must have 8 characters in your password");
                return;
            }

            else
            {
                // Searching roll number 
                Cursor c = db.rawQuery("SELECT * FROM login WHERE login_id ='" + username.getText() + "'" +" and "+ " password = '" + password.getText() + "'", null);

                if (c.moveToFirst()) {
                    Intent i = new Intent
                            (LoginActivity.this,
                                    Main2Activity.class);
                    i.putExtra("name", username.getText().toString());
                    i.putExtra("password", password.getText().toString());

                    startActivityForResult(i,4);
                }
                else
                {
                    showMessage("Error", "Invalid Credentials");
                    clearText();
                }


            }
        }

    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText () {

        username.setText("");
        password.setText("");
    }
}
