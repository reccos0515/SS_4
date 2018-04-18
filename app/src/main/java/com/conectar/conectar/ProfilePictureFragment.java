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
        });
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 3);
                editor.apply();
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });
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
                editor.putInt("PROFILEPICTURE", 9);
                editor.apply();
                Toast.makeText(getActivity(), "Cat!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.chameleonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 10);
                editor.apply();
                Toast.makeText(getActivity(), "Chameleon!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.chickenBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 11);
                editor.apply();
                Toast.makeText(getActivity(), "Chicken!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.clownfishBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 12);
                editor.apply();
                Toast.makeText(getActivity(), "Clownfish!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.cobraBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 13);
                editor.apply();
                Toast.makeText(getActivity(), "Cobra!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.cowBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 14);
                editor.apply();
                Toast.makeText(getActivity(), "Cow!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.crabBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 15);
                editor.apply();
                Toast.makeText(getActivity(), "Crab!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.crocodileBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 16);
                editor.apply();
                Toast.makeText(getActivity(), "Crocodile!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.duckBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 17);
                editor.apply();
                Toast.makeText(getActivity(), "Duck!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.elephantBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 18);
                editor.apply();
                Toast.makeText(getActivity(), "Elephant!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.foxBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 19);
                editor.apply();
                Toast.makeText(getActivity(), "Fox!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.frogBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 20);
                editor.apply();
                Toast.makeText(getActivity(), "Frog!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.giraffeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 21);
                editor.apply();
                Toast.makeText(getActivity(), "Giraffe!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.hippopotamusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 22);
                editor.apply();
                Toast.makeText(getActivity(), "Hippopotamus!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.hummingbirdBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 23);
                editor.apply();
                Toast.makeText(getActivity(), "Hummingbird!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.kangarooBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 24);
                editor.apply();
                Toast.makeText(getActivity(), "Kangaroo!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.lionBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 25);
                editor.apply();
                Toast.makeText(getActivity(), "Lion!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.llamaBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 26);
                editor.apply();
                Toast.makeText(getActivity(), "Llama!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.macawBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 27);
                editor.apply();
                Toast.makeText(getActivity(), "Macaw!", Toast.LENGTH_SHORT).show();
            }
        });
                view.findViewById(R.id.monkeyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 28);
                editor.apply();
                Toast.makeText(getActivity(), "Monkey!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.mooseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 29);
                editor.apply();
                Toast.makeText(getActivity(), "Moose!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.mouseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 30);
                editor.apply();
                Toast.makeText(getActivity(), "Mouse!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.octopusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 31);
                editor.apply();
                Toast.makeText(getActivity(), "Octopus!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.ostrichBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 32);
                editor.apply();
                Toast.makeText(getActivity(), "Ostrich!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.owlBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 33);
                editor.apply();
                Toast.makeText(getActivity(), "Owl!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.pandaBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 34);
                editor.apply();
                Toast.makeText(getActivity(), "Panda!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.pelicanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 35);
                editor.apply();
                Toast.makeText(getActivity(), "Pelican!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.penguinBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 36);
                editor.apply();
                Toast.makeText(getActivity(), "Penguin!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.pigBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 37);
                editor.apply();
                Toast.makeText(getActivity(), "Pig!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.rabbitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 38);
                editor.apply();
                Toast.makeText(getActivity(), "Rabbit!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.racoonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 39);
                editor.apply();
                Toast.makeText(getActivity(), "Racoon!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.rhinocerosBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 40);
                editor.apply();
                Toast.makeText(getActivity(), "Rinocerous!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.sharkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 41);
                editor.apply();
                Toast.makeText(getActivity(), "Shark!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.sheepBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 42);
                editor.apply();
                Toast.makeText(getActivity(), "Sheep!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.siberianhuskyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 43);
                editor.apply();
                Toast.makeText(getActivity(), "Siberian Husky!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.slothBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 44);
                editor.apply();
                Toast.makeText(getActivity(), "Sloth!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.snakeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 45);
                editor.apply();
                Toast.makeText(getActivity(), "Snake!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.squirrelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 46);
                editor.apply();
                Toast.makeText(getActivity(), "Squirrel!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.swanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 47);
                editor.apply();
                Toast.makeText(getActivity(), "Swan!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.tigerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 48);
                editor.apply();
                Toast.makeText(getActivity(), "Tiger!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.toucanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 49);
                editor.apply();
                Toast.makeText(getActivity(), "Toucan!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.turtleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 50);
                editor.apply();
                Toast.makeText(getActivity(), "Turtle!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.whaleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("PROFILEPICTURE", 51);
                editor.apply();
                Toast.makeText(getActivity(), "Whale!", Toast.LENGTH_SHORT).show();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
