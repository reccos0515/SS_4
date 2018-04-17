package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
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
 * {@link ProfilePictureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilePictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePictureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfilePictureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePictureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePictureFragment newInstance(String param1, String param2) {
        ProfilePictureFragment fragment = new ProfilePictureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_picture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        view.findViewById(R.id.beeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 1);
                editor.apply();
                Toast.makeText(getActivity(), "Bee!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.batBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 2);
                editor.apply();
                Toast.makeText(getActivity(), "Bat!", Toast.LENGTH_SHORT).show();
            }
        });/*
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });*/
        view.findViewById(R.id.beetleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 4);
                editor.apply();
                Toast.makeText(getActivity(), "Beetle!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.bulldogBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 5);
                editor.apply();
                Toast.makeText(getActivity(), "Bulldog!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.camelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 6);
                editor.apply();
                Toast.makeText(getActivity(), "Camel!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.canaryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 7);
                editor.apply();
                Toast.makeText(getActivity(), "Canary!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.catBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });/*
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });*/
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
