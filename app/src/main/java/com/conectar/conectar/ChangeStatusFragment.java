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

import org.json.JSONException;
import org.json.JSONObject;

import util.UserUtil;

//import org.omg.CORBA.Current;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangeStatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangeStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeStatusFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ChangeStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangeStatusFragment.
     */
    //Rename and change types and number of parameters
    public static ChangeStatusFragment newInstance(String param1, String param2) {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_status, null); //opens the change status screen
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        view.findViewById(R.id.greenStatusBtn).setOnClickListener(new View.OnClickListener() { //green button clicked
            @Override
            public void onClick(View view) { //set status to green
                UserUtil.updateStatus(2, getContext());
//                try{
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
                Toast.makeText(getActivity(), "Status set to green", Toast.LENGTH_LONG).show();
            }
        });
        view.findViewById(R.id.yellowStatusBtn).setOnClickListener(new View.OnClickListener(){ //yellow button clicked
            @Override
            public void onClick(View view){ //set status to yellow
                UserUtil.updateStatus(1, getContext());
                Toast.makeText(getActivity(), "Status set to yellow", Toast.LENGTH_LONG).show();
            }
        });
        view.findViewById(R.id.redStatusBtn).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) { //set status to red
                UserUtil.updateStatus(0, getContext());
                Toast.makeText(getActivity(), "Status set to red", Toast.LENGTH_LONG).show();
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
        //Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
