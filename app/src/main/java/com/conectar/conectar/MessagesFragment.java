package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {


    private OnFragmentInteractionListener mListener; //TODO figure out if this is necessary


    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MessagesFragment.
     */
    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.testButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String interests = "52345121314";
                String viewInterests = "51314122345";
                int numInterests = interests.charAt(0) - '0';
                int viewNumInterests = viewInterests.charAt(0) - '0';
                int currentCommonIntsDisplayed = 0;
                String[] views = new String[5];
                if(numInterests > 0 && viewNumInterests > 0) {
                    for (int i = 0; i < numInterests; i++) {
                        for (int j = 0; j < viewNumInterests; j++) {
                            //compare interests
                            if (interests.charAt(2 * i + 1) == viewInterests.charAt(2 * j + 1) && interests.charAt(2 * i + 2) == viewInterests.charAt(2 * j + 2)) {
                                //update the correct interest
                                if (currentCommonIntsDisplayed == 0) {
                                    views[0] = (Interests.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                                } else if (currentCommonIntsDisplayed == 1) {
                                    views[1] = (Interests.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                                } else if (currentCommonIntsDisplayed == 2) {
                                    views[2] = (Interests.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                                } else if (currentCommonIntsDisplayed == 3) {
                                    views[3] = (Interests.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                                } else if (currentCommonIntsDisplayed == 4) {
                                    views[4] = (Interests.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                                }
                                currentCommonIntsDisplayed++;
                            }
                        }
                    }
                }
                String msg = "";
                for(int i = 0; i < currentCommonIntsDisplayed; i++){
                    msg += views[i] + " ";
                }
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
