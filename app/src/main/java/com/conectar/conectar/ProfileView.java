package com.conectar.conectar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        //text to be set to the username
        String text = "trial";
        //edit this to set the username
        TextView username = findViewById(R.id.viewUsername);
        username.setText(text);
    }
}
