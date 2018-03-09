package util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import com.conectar.conectar.R;



/**
 * Created by Maggie on 2/14/2018.
 * Methods that implement features that are useful when dealing with fragments
 */

public class FragmentUtil extends Fragment {



    public void refreshFragment(Fragment fragment){ //TODO implement refreshing

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(fragment).attach(fragment).commit();

    }

    //TODO change fragment
    public void changeFragment(Fragment fragment){
        /*
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, fragment);
        fragmentTransaction.commit();
        */
    }

    //TODO refresh fragment
    //TODO scrolling???



}
