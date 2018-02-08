package com.conectar.conectar;

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

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.navigation, menu);
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

        if (id == R.id.nav_messages) { // Go to messages screen

        } else if (id == R.id.nav_settings) { //Go to settings screen

        } else if (id == R.id.nav_editProfile) { //Go to edit profile screen

        } else if (id == R.id.nav_report) { //Go to report screen

        } else if (id == R.id.nav_search) { //Go to search screen

        } else if (id == R.id.nav_changeStatus) { //Go to change status screen

        } else if (id == R.id.nav_login) { //Go to change login screen

        } else if (id == R.id.nav_logout) { //Go to change logout screen

        } else if (id == R.id.nav_newProfile) { //Go to change new profile screen

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Grabs screens for the menu
    public void getSettingsScreen(MenuItem item){ //Opens the settings activity screen
        Intent getSettingsScreenIntent = new Intent(this, SettingsActivity.class);
        startActivity(getSettingsScreenIntent);
    }

    public void getSearchScreen(MenuItem item){ // Opens the search activity screen
        Intent getSearchScreenIntent = new Intent(this, SearchActivity.class);
        startActivity(getSearchScreenIntent);
    }

    public void getEditProfileStatusScreen(MenuItem item){ // Opens the edit profile activity screen
        Intent getEditProfileStatusScreenIntent = new Intent(this, EditProfileStatus.class);
        startActivity(getEditProfileStatusScreenIntent);
    }

    public void getReportScreen(MenuItem item){ // Opens the report activity screen
        Intent getReportScreenIntent = new Intent(this, ReportActivity.class);
        startActivity(getReportScreenIntent);
    }

    public void getNewProfileScreen(MenuItem item){ // Opens the new profile activity screen
        Intent getNewProfileScreenIntent = new Intent(this, New_Profile.class);
        startActivity(getNewProfileScreenIntent);
    }

    public void getMessagesScreen(MenuItem item){
        Intent getMessagesScreenIntent = new Intent(this, MessagesActivity.class);
        startActivity(getMessagesScreenIntent);
    }

    public void getLogoutScreen(MenuItem item){
        Intent getLogoutScreenIntent = new Intent(this, LogoutActivity.class);
        startActivity(getLogoutScreenIntent);
    }

    public void getLoginScreen(MenuItem item){
        Intent getLoginScreenIntent = new Intent(this, LoginActivity.class);
        startActivity(getLoginScreenIntent);
    }

    public void getChangeStatusScreen(MenuItem item){
        Intent getChangeStatusScreenIntent = new Intent(this, ChangeStatusActivity.class);
        startActivity(getChangeStatusScreenIntent);
    }
}
