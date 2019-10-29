package com.example.kuttr.stockpredictor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SignupActivity";
    SQLiteDatabase db;
    EditText e1,e2,e3,e4,e5;
    TextView t1;
    Button b1;
    CheckBox checkbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        e1=findViewById(R.id.input_loginid);
        e2=findViewById(R.id.input_name);
        e3=findViewById(R.id.input_email);
        e4=findViewById(R.id.input_password);
        e5=findViewById(R.id.input_phone_number);

        checkbox=findViewById(R.id.cb);

        b1=findViewById(R.id.btn_signup);

        t1=findViewById(R.id.link_login) ;

        db = openOrCreateDatabase
                ("SPLogin", Context.MODE_PRIVATE, null);
        db.execSQL
                ("CREATE TABLE IF NOT EXISTS " +
                        "login(login_id VARCHAR PRIMARY KEY," +
                        "name VARCHAR,"+"email_id VARCHAR,"+"phone_no Number(10),"+"password VARCHAR);");

        b1.setOnClickListener(this);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    e4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    e4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();


            }
        });

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        b1.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(signup.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String login_id = e1.getText().toString();
        String name = e2.getText().toString();
        String email = e3.getText().toString();
        String password = e4.getText().toString();
        String phone = e5.getText().toString();

        Intent i = new Intent
                (signup.this,
                        LoginActivity.class);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
        clearText();
    }

    public void onSignupSuccess() {
        b1.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        b1.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String login_id = e1.getText().toString();
        String name = e2.getText().toString();
        String email = e3.getText().toString();
        String password = e4.getText().toString();
        String phone = e5.getText().toString();


        if (login_id.isEmpty()) {
            e1.setError("at least 5 characters");
            valid = false;
        } else {
            e1.setError(null);
        }

        if (name.isEmpty() || name.length() < 3) {
            e2.setError("at least 3 characters");
            valid = false;
        }
        else {
            e2.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e3.setError("enter a valid email address");
            valid = false;
        }
        else {
            e3.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            e5.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            e5.setError(null);
        }

        if (phone.isEmpty() || phone.length() < 10) {
            e4.setError("10 numbers required");
            valid = false;
        } else {
            e4.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId() == b1.getId())
        {
            // Checking empty fields
            if (e1.getText().toString().trim().length() == 0 ||
                    e3.getText().toString().trim().length() == 0 || e5.getText().toString().trim().length() == 0 ||
                    e2.getText().toString().trim().length() == 0 || e1.getText().toString().trim().length() == 0
                    || e4.getText().toString().trim().length() == 0 &&
                    e3.getText().toString().trim().length() == 0 &&
                    e2.getText().toString().trim().length() == 0 && e1.getText().toString().trim().length() == 0 && e5.getText().toString().trim().length() == 0)
            {
                showMessage("Error", "Please Enter all the values");
                return;
            }

            // Inserting record 
            db.execSQL("INSERT INTO login VALUES('" + e1.getText() + "','" + e2.getText() +"','" + e3.getText() +
                    "','" + e5.getText() +"','"+ e4.getText() + "');");

            showMessage("Success", "Record added");
            signup();

        }

        else
        {
            showMessage("Error", "Constraints Violated");
            clearText();
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

        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");

    }
}
