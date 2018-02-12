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
    private ArrayList<String> interests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_status);
        bio = findViewById(R.id.bio);
        newInterest = findViewById(R.id.newInterest);
        Button button = findViewById(R.id.submitInterest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interests.size() < 6){
                    interests.add(newInterest.getText().toString());
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Interests are full";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
