package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import util.RequestJsonObject;
import util.Singleton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView jsonTestMsg;
    String serverUrl = "http://proj-309-ss-4.cs.iastate.edu:9001/test";


    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
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
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = getView().findViewById(R.id.addUserReq);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject js = new JSONObject();
                try {
                    js.put("id", "4");
                    js.put("userName","Ollie");
                    js.put("bio", "Ollie likes mangos");
                    //js.put("friendsTo", "1");
                    //js.put("friendsOf", "2");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //can use same request queue?
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://proj-309-ss-4.cs.iastate.edu:9001/users",  js, //may need typecasting to string on the null?
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                /*
                                try { //will always give exception, is why need try catch
                                    jsonTestMsg.setText(response.getString("userName")); //not sure if case sensitive or not on the string input
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                */

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
                Singleton.getmInstance(getContext()).addToRequestQueue(jsonObjectRequest); //add json to queue
            }
        });

        //--------------------------------------------------GET REQUEST----------------------------------------------------------------------------------//

        Button button2 = getView().findViewById(R.id.getUserBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject js = new JSONObject();
                try {
                    js.put("id", "4");
                    js.put("userName","Ollie");
                    js.put("bio", "Ollie likes mangos");
                    //js.put("friendsTo", "1");
                    //js.put("friendsOf", "2");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //can use same request queue?
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://proj-309-ss-4.cs.iastate.edu:9001/users",  null, //may need typecasting to string on the null?
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                
                                try { //will always give exception, is why need try catch
                                    jsonTestMsg.setText(response.getString("userName")); //not sure if case sensitive or not on the string input
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
                Singleton.getmInstance(getContext()).addToRequestQueue(jsonObjectRequest); //add json to queue
            }
        });


        //-----------------------------------------------------OLD CODE----------------------------------------------------------------------------------------//
        /*
        view.findViewById(R.id.addUserReq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Timer().schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                //RequestJsonObject.postProfileRequest(getContext(), "http://localhost/SS_4/echo.php");
                                //jsonTestMsg = (TextView) getView().findViewById(R.id.jsonTestMsg);
                                RequestJsonObject.postProfileRequestJSON(getContext(), "proj-309-ss-4.cs.iastate.edu:9001/users", view);
                            }
                        }, 20000
                );
                System.out.println(RequestJsonObject.getRequests(getContext()));
            }
        });
        */
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
