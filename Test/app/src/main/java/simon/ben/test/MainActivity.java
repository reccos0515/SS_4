package simon.ben.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonTap(View v){
        Toast myToast = Toast.makeText(
                getApplicationContext(),
                "Ouch!",
                Toast.LENGTH_LONG);
        myToast.show();

//        Toast toast = Toast.makeText(context, R.string.string_message_id, Toast.LENGTH_LONG);
//        View view = toast.getView();
//        view.setBackgroundResource(R.drawable.custom_backgrround);
//        TextView text = (TextView) view.findViewById(android.R.id.message);
//    /*Here you can do anything with above textview like text.setTextColor(Color.parseColor("#000000"));*/
//        toast.show();
    }
}
