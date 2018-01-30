package com.example.jabader.fauxproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Receive message", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
                fab2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view2) {
                        Snackbar.make(view2, "Receive message", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
                        fab3.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view3) {
                                Snackbar.make(view3, "Receive message", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.fab4);
                                fab4.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view4) {
                                        Snackbar.make(view4, "Receive message", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                        FloatingActionButton fab5 = (FloatingActionButton) findViewById(R.id.fab5);
                                        fab5.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View view5) {
                                                Snackbar.make(view5, "Receive message", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                                FloatingActionButton fab6 = (FloatingActionButton) findViewById(R.id.fab6);
                                                fab6.setOnClickListener(new View.OnClickListener(){
                                                    @Override
                                                    public void onClick(View view6) {
                                                        Snackbar.make(view6, "Receive message", Snackbar.LENGTH_LONG)
                                                                .setAction("Action", null).show();
                                                        FloatingActionButton fab7 = (FloatingActionButton) findViewById(R.id.fab7);
                                                        fab7.setOnClickListener(new View.OnClickListener(){
                                                            @Override
                                                            public void onClick(View view7) {
                                                                Snackbar.make(view7, "Receive message", Snackbar.LENGTH_LONG)
                                                                        .setAction("Action", null).show();
                                                                FloatingActionButton fab8 = (FloatingActionButton) findViewById(R.id.fab8);
                                                                fab8.setOnClickListener(new View.OnClickListener(){
                                                                    @Override
                                                                    public void onClick(View view8) {
                                                                        Snackbar.make(view8, "Receive message", Snackbar.LENGTH_LONG)
                                                                                .setAction("Action", null).show();
                                                                        startActivity(new Intent(MainActivity.this, SecondActivity.class));
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });

                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        Context context2 = getApplicationContext();
        CharSequence message2 = "You received a message!";
        int duration2 = Toast.LENGTH_SHORT;
        Toast toast2 = Toast.makeText(context2, message2, duration2);
        toast2.show();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
