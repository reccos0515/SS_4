package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AboutFragment.
     */
    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
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
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView answer = view.findViewById(R.id.about_answer);
        view.findViewById(R.id.about_seenAll).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer.setText("The creators of coNECTAR believe it's important that you know you've seen everyone in this setting. If you'd like to look again at users you've seen before, you can change to a different status and back. Then you'll be able to see everyone all over again!");
            }
        });
        view.findViewById(R.id.about_whoMade).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer.setText("Creators of coNECTAR include Tristan Anderson, Jessie Bader, Maggie Dalton, and Ben Simon. The images for coNECTAR were found at www.freepik.com. Icons made by Freepik. Freepik is licenced by Creative Commons BY 3.0. A special thanks goes out to their TA Shruti Sahu and their professor Simanta Mitra");
            }
        });
        view.findViewById(R.id.about_redStatus).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer.setText("A red status is a great way to stay private--only your friends can see you when you're red! However, it's only fair if you can't see anyone else either");
            }
        });
        view.findViewById(R.id.about_statuses).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer.setText("Statuses let you control who you see and how they see you! If your status is green, you can see any users. If your status is yellow, you'll only see users with at least one matching interest. If your status is red, no one can see you but your friends (but you can't see anyone either)");
            }
        });
        view.findViewById(R.id.about_whatIs).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer.setText("coNECTAR is an app designed to bring people together. It aims to pair people who wouldn't have met otherwise and inspire people to try new things.");
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
        void onFragmentInteraction(Uri uri);
    }
}
