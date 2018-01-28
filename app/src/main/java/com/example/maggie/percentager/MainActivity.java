package com.example.maggie.percentager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //need to connect buttons from xml to code
    TextView totalTextView;
    EditText percentText;
    EditText numberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //called automatically to create views from xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        totalTextView = (TextView) findViewById(R.id.totalTextView);
        percentText = (EditText) findViewById(R.id.percentText);
        numberText = (EditText) findViewById(R.id.numberText);

        Button calcBtn = (Button) findViewById(R.id.calcBtn); //references the button from xml to code
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                float percentage = Float.parseFloat(percentText.getText().toString());
                float dec = percentage / 100; //converts percentage to a decimal
                float total = dec * Float.parseFloat(numberText.getText().toString());
                totalTextView.setText(Float.toString(total)); //converts the total to a string and sets it to the top text
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
