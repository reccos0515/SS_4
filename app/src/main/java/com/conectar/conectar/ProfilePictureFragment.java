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
import android.widget.Toast;

import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilePictureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilePictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePictureFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public ProfilePictureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfilePictureFragment.
     */
    public static ProfilePictureFragment newInstance(String param1, String param2) {
        ProfilePictureFragment fragment = new ProfilePictureFragment();
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
        return inflater.inflate(R.layout.fragment_profile_picture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize preferences
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)

        //to submit, go back to the edit profile page
        view.findViewById(R.id.profPic_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new EditProfileFragment());
                fragmentTransaction.commit();
            }
        });
        //for each profile picture, set the int value in UserUtil and toast
        view.findViewById(R.id.beeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(1);
                Toast.makeText(getActivity(), "Bee!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.batBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(2);
                Toast.makeText(getActivity(), "Bat!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.beaverBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(3);
                Toast.makeText(getActivity(), "Beaver!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.beetleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(4);
                Toast.makeText(getActivity(), "Beetle!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.bulldogBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(5);
                Toast.makeText(getActivity(), "Bulldog!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.camelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(6);
                Toast.makeText(getActivity(), "Camel!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.canaryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(7);
                Toast.makeText(getActivity(), "Canary!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.catBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(9);
                Toast.makeText(getActivity(), "Cat!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.chameleonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(10);
                Toast.makeText(getActivity(), "Chameleon!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.chickenBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(11);
                Toast.makeText(getActivity(), "Chicken!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.clownfishBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(12);
                Toast.makeText(getActivity(), "Clownfish!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.cobraBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(13);
                Toast.makeText(getActivity(), "Cobra!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.cowBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(14);
                Toast.makeText(getActivity(), "Cow!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.crabBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(15);
                Toast.makeText(getActivity(), "Crab!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.crocodileBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(16);
                Toast.makeText(getActivity(), "Crocodile!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.duckBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(17);
                Toast.makeText(getActivity(), "Duck!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.elephantBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(18);
                Toast.makeText(getActivity(), "Elephant!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.foxBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(19);
                Toast.makeText(getActivity(), "Fox!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.frogBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(20);
                Toast.makeText(getActivity(), "Frog!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.giraffeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(21);
                Toast.makeText(getActivity(), "Giraffe!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.hippopotamusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(22);
                Toast.makeText(getActivity(), "Hippopotamus!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.hummingbirdBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(23);
                Toast.makeText(getActivity(), "Hummingbird!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.kangarooBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(24);
                Toast.makeText(getActivity(), "Kangaroo!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.lionBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(25);
                Toast.makeText(getActivity(), "Lion!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.llamaBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(26);
                Toast.makeText(getActivity(), "Llama!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.macawBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(27);
                Toast.makeText(getActivity(), "Macaw!", Toast.LENGTH_SHORT).show();
            }
        });
                view.findViewById(R.id.monkeyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(28);
                Toast.makeText(getActivity(), "Monkey!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.mooseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(29);
                Toast.makeText(getActivity(), "Moose!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.mouseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(30);
                Toast.makeText(getActivity(), "Mouse!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.octopusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(31);
                Toast.makeText(getActivity(), "Octopus!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.ostrichBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(32);
                Toast.makeText(getActivity(), "Ostrich!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.owlBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(33);
                Toast.makeText(getActivity(), "Owl!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.pandaBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(34);
                Toast.makeText(getActivity(), "Panda!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.pelicanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(35);
                Toast.makeText(getActivity(), "Pelican!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.penguinBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(36);
                Toast.makeText(getActivity(), "Penguin!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.pigBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(37);
                Toast.makeText(getActivity(), "Pig!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.rabbitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(38);
                Toast.makeText(getActivity(), "Rabbit!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.racoonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(39);
                Toast.makeText(getActivity(), "Racoon!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.rhinocerosBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(40);
                Toast.makeText(getActivity(), "Rinocerous!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.sharkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(41);
                Toast.makeText(getActivity(), "Shark!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.sheepBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(42);
                Toast.makeText(getActivity(), "Sheep!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.siberianhuskyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(43);
                Toast.makeText(getActivity(), "Siberian Husky!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.slothBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(44);
                Toast.makeText(getActivity(), "Sloth!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.snakeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(45);
                Toast.makeText(getActivity(), "Snake!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.squirrelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(46);
                Toast.makeText(getActivity(), "Squirrel!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.swanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(47);
                Toast.makeText(getActivity(), "Swan!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.tigerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(48);
                Toast.makeText(getActivity(), "Tiger!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.toucanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(49);
                Toast.makeText(getActivity(), "Toucan!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.turtleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(50);
                Toast.makeText(getActivity(), "Turtle!", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.whaleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.setUserProfPic(51);
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
        void onFragmentInteraction(Uri uri);
    }
}
