package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LogoutFragment.OnFragmentInteractionListener,
            EditProfileFragment.OnFragmentInteractionListener, ChangeStatusFragment.OnFragmentInteractionListener,
            FriendsFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener,
            NewProfileFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener,
            SwipeFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener,
            ProfileViewFragment.OnFragmentInteractionListener{
    /*Added Logout, Edit Profile, Change Status, Friends, Messages, New Profile, Search, Swipe Screen/Home, Login
    to drawer navigation */

    String currentUsername, currentId, currentBio;
    String prefUsername, prefId, prefBio; //temp variable that is set based on whether or not there are stored preferences

    @Override
    public void onFragmentInteraction(Uri uri){
        //empty
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //grabs the variables stored from the app killing itself
        if(savedInstanceState != null){
           //currentUsername = savedInstanceState.getString("USERNAME");
           currentUsername = savedInstanceState.getString("USERNAME");
           currentId = savedInstanceState.getString("ID");
           currentBio = savedInstanceState.getString("BIO");
        }

        //grabs the variables stored from the user killing the app
        prefUsername = getPreferences(Context.MODE_PRIVATE).getString("USERNAME", "EMPTY");
        if(!prefUsername.equals("EMPTY")){ //If there actually is a stored value
            //set the local variables
            currentUsername = prefUsername;
        }
        prefBio = getPreferences(Context.MODE_PRIVATE).getString("BIO", "EMPTY");
        if(!prefBio.equals("EMPTY")){
            currentBio = prefBio;
        }
        prefId = getPreferences(Context.MODE_PRIVATE).getString("ID", "EMPTY");
        if(!prefId.equals("EMPTY")){
            currentId = prefId;
        }


        //sets the navigation drawer as initial layout
        setContentView(R.layout.activity_main);

        //creates and sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //creates and sets the navigation drawer to toggle on the toolbar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //sets up the drawer as a navigation menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //sets default page to the profile view page
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.screen_area, new ProfileViewFragment());
        tx.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { //called when the app kills itself that it saves information (also for orientation changes)
        outState.putString("USERNAME", currentUsername); //saves last value
        outState.putString("BIO", currentBio);
        outState.putString("ID", currentId);
        super.onSaveInstanceState(outState);
    }

    /**
     * Saves any information that needs to be kept when a user kills the application (maybe when they change fragments too?)
     */
    private void saveSettings(){
        SharedPreferences.Editor sharedEditor = getPreferences(Context.MODE_PRIVATE).edit();
        sharedEditor.putString("USERNAME", currentUsername);
        sharedEditor.putString("BIO", currentBio);
        sharedEditor.putString("ID", currentId);
        //sharedEditor.commit();
        //commit writes to storage immediately and apply does it in the background, otherwise the same
        sharedEditor.apply();
    }

    @Override
    protected void onStop() { //tells the app that it was force closed by the user
        saveSettings(); //save the settings before actually killing the app
        super.onStop();
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
    public boolean onNavigationItemSelected(MenuItem item) { //tells the app to switch screens if certain menu buttons are pressed
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        int id = item.getItemId();

        //if nav______ is clicked, switch to its corresponding fragment page
        if (id == R.id.nav_search) {
            fragment = new SearchFragment();
        } else if (id == R.id.nav_messages) {
            fragment = new MessagesFragment();
        } else if (id == R.id.nav_changeStatus) {
            fragment = new ChangeStatusFragment();
        } else if (id == R.id.nav_editProfile) {
            fragment = new EditProfileFragment();
        } else if (id == R.id.nav_settings) { //needs fragment implementation
            //waiting for xml implementation
        } else if (id == R.id.nav_logout) {
            fragment = new LogoutFragment();
        } else if (id == R.id.nav_login) {
            fragment = new LoginFragment();
        } else if (id == R.id.nav_swipe) {
            fragment = new SwipeFragment();
        } else if (id == R.id.nav_newProfile) {
            fragment = new NewProfileFragment();
        } else if (id == R.id.nav_about) { //TODO fix

        } else if (id == R.id.nav_report) {
            //waiting for xml implementation
        } else if (id == R.id.nav_friends) {
            fragment = new FriendsFragment();
        }

        if(fragment != null){ //Changes the screens
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
