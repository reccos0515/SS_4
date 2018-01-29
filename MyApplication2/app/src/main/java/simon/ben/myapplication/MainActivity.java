package simon.ben.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button btnDefaultToast, btnCustomToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDefaultToast = (Button) findViewById(R.id.btnDefaultToast);
        btnCustomToast = (Button) findViewById(R.id.btnCustomToast);
    }


    @Override
    public void onClick(View view) {
        if (view.equals(btnDefaultToast)) {
            Toast.makeText(MainActivity.this, "This is default toast message", Toast.LENGTH_SHORT).show();
        } else if (view.equals(btnCustomToast)) {
            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.content_custom_toast, (ViewGroup) findViewById(R.id.llCustom));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();
        }
    }
}
