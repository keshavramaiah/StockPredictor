package com.example.kuttr.stockpredictor;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class settings extends AppCompatActivity {
    Activity a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu
            (Menu menu) {
        getMenuInflater().inflate
                (R.menu.help_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected
            (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.i1:
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"kuttralamvc@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding App Bug");
                intent.putExtra(Intent.EXTRA_TEXT,"");
                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
                Toast.makeText
                        (this, "Email clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.i2:

                Toast.makeText
                        (this, "Message clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(settings.this,Message.class);
                startActivity(i);
                break;

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}