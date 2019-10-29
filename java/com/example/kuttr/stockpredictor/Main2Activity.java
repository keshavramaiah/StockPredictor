package com.example.kuttr.stockpredictor;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerlayout;
    ActionBarDrawerToggle mToggle;
    GridView gridView;
    Toolbar toolbar;
    String n,p;
    NavigationView nav_view;

    String letterList[] = {"Stock Predictor", "Calculator", "History", "Settings", "About"};

    int letterIcon[] = {R.drawable.stock_predictor, R.drawable.cal, R.drawable.history, R.drawable.settings, R.drawable.about};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nav_view = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav_view.getMenu().getItem(0).setChecked(true);

        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, toolbar, R.string.Open, R.string.Close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();



        gridView = findViewById(R.id.gridview1);
        GridAdapter adapter = new GridAdapter(Main2Activity.this, letterIcon, letterList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(letterList[position] == letterList[0])//stock predictor
                {
                    Intent i = new Intent(Main2Activity.this, predictor.class);
                    startActivity(i);
                }
                if(letterList[position] == letterList[1])//calculator
                {
                    Intent i = new Intent(Main2Activity.this, calculator2.class);
                    startActivity(i);
                }
                if(letterList[position] == letterList[2])//history
                {
                    Intent i = new Intent(Main2Activity.this, recents2.class);
                    startActivity(i);
                }
                if(letterList[position] == letterList[3])//settings
                {
                    Intent i = new Intent(Main2Activity.this, settings2.class);
                    startActivity(i);
                }
                if(letterList[position] == letterList[4])//About
                {
                    Intent i = new Intent(Main2Activity.this, About2.class);
                    startActivity(i);
                }

            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {

        }
        else if (id == R.id.nav_favourite) {
            Intent i = new Intent(Main2Activity.this, favourites2.class);
            startActivity(i);

        }
        else if (id == R.id.nav_History) {
            Intent i = new Intent(Main2Activity.this, recents2.class);
            startActivity(i);
        }
        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            StringBuilder sb = new StringBuilder();
            sb.append("Hi, I am using Stock Predictor. I like this and I want you to check it out.");
            sb.append("https://play.google.com/store/apps/details?id=" + this.getBaseContext().getPackageName());
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Test");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, sb.toString());
            startActivity(Intent.createChooser(sharingIntent, "Share with"));
        }
        else if (id == R.id.nav_about) {
            Intent i = new Intent(Main2Activity.this, About2.class);
            startActivity(i);
        }
        else if(id==R.id.nav_logout)
        {
            Intent i = new Intent(Main2Activity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.power:
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);return true;
                default:return super.onOptionsItemSelected(item);

        }
    }
}

