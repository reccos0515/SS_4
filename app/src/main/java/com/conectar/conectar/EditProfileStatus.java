package com.conectar.conectar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditProfileStatus extends AppCompatActivity {
    private EditText bio;
    private EditText newInterest;
    private int numInterests;
    private String[] interests = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_status);
        //create variables that take input from UI
        bio = findViewById(R.id.bio);
        newInterest = findViewById(R.id.newInterest);
        //int to keep track of the current number of interests
        numInterests = 0;
        //create a button to add an interest
        Button button = findViewById(R.id.submitInterest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if there are not yet 5 interests, it can be added
                if(numInterests < 5){
                    //toast to let the user know it worked
                    Context context = getApplicationContext();
                    CharSequence text = "Interests are not full";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //add the interest
                    interests[numInterests] = newInterest.getText().toString();
                    //increment the number of interests
                    numInterests++;
                }
                else{
                    //let the user know there are too many interests to add another
                    Context context = getApplicationContext();
                    CharSequence text = "Interests are full";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        //button to delete interest 1
        Button button1 = findViewById(R.id.interestOne);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check there is an interest 1
                if(numInterests >= 1){
                    //move every interest down one place
                    for(int i = 0; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        //button to delete interest 2
        Button button2 = findViewById(R.id.interestTwo);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check there is an interest 2
                if(numInterests >= 2){
                    //move every interest from the second interest up, down one place
                    for(int i = 1; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        //button to delete interest 3
        Button button3 = findViewById(R.id.interestThree);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check there is an interest 3
                if(numInterests >= 3){
                    //move every interest from the third interest up, down one place
                    for(int i = 2; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        //button to delete interest 4
        Button button4 = findViewById(R.id.interestFour);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check there is an interest 4
                if(numInterests >= 4){
                    //move every interest from the fourth interest up, down one place
                    for(int i = 3; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        //button to delete interest 5
        Button button5 = findViewById(R.id.interestFive);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set the fifth interest to be null
                interests[5] = null;
                //decrement number of interests
                numInterests--;
            }
        });
    }
}
