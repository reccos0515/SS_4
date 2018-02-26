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




}
