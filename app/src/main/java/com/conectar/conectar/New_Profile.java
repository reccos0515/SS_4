package com.conectar.conectar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class New_Profile extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__profile);
        //set all variables to take input from the UI
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        //create the submit button
        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a toast
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    //if confirmPassword and password are the same, submit
                    text = "Successfully submitted";
                }
                else{
                    //if confirmPassword and password are different, do not submit
                    text = "Please match passwords";
                }
                //toast whether or not it was submitted
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


    }
}
