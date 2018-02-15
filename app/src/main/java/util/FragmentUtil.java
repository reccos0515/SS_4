package util;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by Maggie on 2/14/2018.
 */

public class FragmentUtil extends Fragment {

    public void refreshFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(fragment).attach(fragment).commit();

    }


}
