package com.example.kuttr.stockpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class calculator2 extends AppCompatActivity implements View.OnClickListener {

    EditText et1,et2,et3,et4,et5,et6,et7;
    Button b1;
    String y;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        et7=findViewById(R.id.et7);
        b1=findViewById(R.id.b1);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==b1.getId())
        {

            if(TextUtils.isEmpty(et1.getText())) {
                et1.setError("You must enter a number");


                if (TextUtils.isEmpty(et2.getText())) {
                    et2.setError("You must enter a number");


                    if (TextUtils.isEmpty(et3.getText())) {
                        et3.setError("You must enter a number");


                        if (TextUtils.isEmpty(et4.getText())) {
                            et4.setError("You must enter a number");


                            if (TextUtils.isEmpty(et5.getText())) {
                                et5.setError("You must enter a number");


                                if (TextUtils.isEmpty(et6.getText())) {
                                    et6.setError("You must enter a number");


                                    if (TextUtils.isEmpty(et1.getText()) && TextUtils.isEmpty(et2.getText()) &&
                                            TextUtils.isEmpty(et3.getText()) && TextUtils.isEmpty(et4.getText()) &&
                                            TextUtils.isEmpty(et5.getText()) && TextUtils.isEmpty(et6.getText())) {

                                        et1.setError("You must enter a number");
                                        et2.setError("You must enter a number");
                                        et3.setError("You must enter a number");
                                        et4.setError("You must enter a number");
                                        et5.setError("You must enter a number");
                                        et6.setError("You must enter a number");
                                        y = "0";
                                        et7.setText(y);

                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else{
                String t=et1.getText().toString();
                int e1=Integer.parseInt(t);
                t=et2.getText().toString();
                int e2=Integer.parseInt(t);
                t=et3.getText().toString();
                int e3=Integer.parseInt(t);
                t=et4.getText().toString();
                int e4=Integer.parseInt(t);
                t=et5.getText().toString();
                int e5=Integer.parseInt(t);
                t=et6.getText().toString();
                int e6=Integer.parseInt(t);
                int m=((e3*e1)-e5)-((e2*e1)+e4);//((sell price per share*no 0f share)-Sell commission)-((Buying price per share*number of shares)+Buy commission)//
                y=""+m;
                et7.setText(y);}
        }

    }

}
