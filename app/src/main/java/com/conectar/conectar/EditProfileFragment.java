package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import util.InterestsUtil;
import util.JsonRequest;
import util.SessionUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EditProfileFragment extends Fragment {

    private EditText bio; //bio to be edited by user
    private EditText newInterest; //todo this must be changed
    private Button int1; //interest 1 button
    private Button int2; //interest 2 button
    private Button int3; //interest 3 button
    private Button int4; //interest 4 button
    private Button int5; //interest 5 button
    private String interests; //String with user's interests
    private int numInterests; //int with number of interest user currently has
    private final int id = 1; //todo reset this to get from session variable
    private final String empty = "(empty)"; //string to set text view to if empty
    private final String username = "test"; //string with username todo reset this to get from session variable
    private Context context; //application context

    private OnFragmentInteractionListener mListener;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LogoutFragment.
     */
    //Rename and change types and number of parameters
    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //------------------------------------------------------------------------------onCreate
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //create variables that take input from UI
        bio = view.findViewById(R.id.bio);
        newInterest = view.findViewById(R.id.newInterest);
        int1 = view.findViewById(R.id.interestOne);
        int2 = view.findViewById(R.id.interestTwo);
        int3 = view.findViewById(R.id.interestThree);
        int4 = view.findViewById(R.id.interestFour);
        int5 = view.findViewById(R.id.interestFive);

        context = getActivity().getApplicationContext(); //get the context

        //set all the user info
//        bio.setText(SessionUtil.getSessionBio()); TODO put this back in once session variables work
//        interests = SessionUtil.getSessionInterests(); TODO put this back in once session variables work
        String defaultBio = "this is my bio"; //todo remove this
        bio.setText(defaultBio); //todo remove this
        interests = "41213141500"; //todo remove this
        updateInterestButtons(); //update the ui on the buttons

        numInterests = interests.charAt(0) - '0'; //set the number of interests
        //create a button to add an interest
        view.findViewById(R.id.submitNewInterest).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //if there are not yet 5 interests, it can try to be added
                if(numInterests < 5){
                    String interestToAdd = newInterest.getText().toString(); //get the new interest number
                    //if this is a valid interest, it can be added
                    if(InterestsUtil.getInterest(interestToAdd) != null){
                        char[] interestChars = interests.toCharArray(); //convert interests to a char array
                        interestChars[(2 * numInterests) + 1] = interestToAdd.charAt(0); //replace the old numbers with the new id
                        interestChars[(2 * numInterests) + 2] = interestToAdd.charAt(1);
                        numInterests++; //increment the number of interests
                        interestChars[0] = (char) (numInterests + '0'); //update numInterests in the string
                        interests = String.valueOf(interestChars); //turn it back into a string
                        Toast.makeText(getContext(), "Successfully added " + InterestsUtil.getInterest(interestToAdd), Toast.LENGTH_SHORT); //toast to let the user know it worked
                        updateInterestButtons(); //update the ui on the buttons to remove interests
                    }
                    else{
                        Toast.makeText(getContext(), "Please enter a valid interest number", Toast.LENGTH_SHORT).show(); //toast to tell the user the number was not valid
                    }
                }
                else{
                    Toast.makeText(context, "Interests are full", Toast.LENGTH_SHORT).show(); //let the user know interests were full
                }
            }
        });


        view.findViewById(R.id.interestOne).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 1){
                    char[] interestChars = interests.toCharArray(); //convert interests to a char array
                    //move every interest down in the string
                    for(int i = 1; i < 9; i++){
                        interestChars[i] = interestChars[i + 2];
                    }
                    interestChars[9] = '0'; //fill in last interest as 0
                    interestChars[10] = '0';
                    numInterests--; //decrement number of interests
                    interestChars[0] = (char) numInterests; //reset the number of interests in the string
                    interests = String.valueOf(interestChars); //return to a string
                    updateInterestButtons(); //update ui on buttons to remove interests
                }
            }
        });
        view.findViewById(R.id.interestTwo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 2){
                    char[] interestChars = interests.toCharArray(); //convert interests to a char array
                    //move every interest down in the string
                    for(int i = 3; i < 9; i++){
                        interestChars[i] = interestChars[i + 2];
                    }
                    interestChars[9] = '0'; //fill in last interest as 0
                    interestChars[10] = '0';
                    numInterests--; //decrement number of interests
                    interestChars[0] = (char) numInterests; //reset the number of interests in the string
                    interests = String.valueOf(interestChars); //return to a string
                    updateInterestButtons(); //update ui on buttons to remove interests
                }
            }
        });
        view.findViewById(R.id.interestThree).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 3){
                    char[] interestChars = interests.toCharArray(); //convert interests to a char array
                    //move every interest down in the string
                    for(int i = 5; i < 9; i++){
                        interestChars[i] = interestChars[i + 2];
                    }
                    interestChars[9] = '0'; //fill in last interest as 0
                    interestChars[10] = '0';
                    numInterests--; //decrement number of interests
                    interestChars[0] = (char) numInterests; //reset the number of interests in the string
                    interests = String.valueOf(interestChars); //return to a string
                    updateInterestButtons(); //update ui on buttons to remove interests
                }
            }
        });
        view.findViewById(R.id.interestFour).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 4){
                    char[] interestChars = interests.toCharArray(); //convert interests to a char array
                    //move every interest down in the string
                    for(int i = 7; i < 9; i++){
                        interestChars[i] = interestChars[i + 2];
                    }
                    interestChars[9] = '0'; //fill in last interest as 0
                    interestChars[10] = '0';
                    numInterests--; //decrement number of interests
                    interestChars[0] = (char) numInterests; //reset the number of interests in the string
                    interests = String.valueOf(interestChars); //return to a string
                    updateInterestButtons(); //update ui on buttons to remove interests
                }
            }
        });
        view.findViewById(R.id.interestFive).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 5){
                    char[] interestChars = interests.toCharArray(); //convert interests to a char array
                    interestChars[9] = '0'; //fill in last interest as 0
                    interestChars[10] = '0';
                    numInterests--; //decrement number of interests
                    interestChars[0] = (char) numInterests; //reset the number of interests in the string
                    interests = String.valueOf(interestChars); //return to a string
                    updateInterestButtons(); //update ui on buttons to remove interest
                }
            }
        });
        //For debugging, outputs all the current values in interests
        view.findViewById(R.id.updateServer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                JSONObject js = new JSONObject(); //json object to send, fill will current fields
                try{
                    js.put("interests", interests);
                    js.put("bio", bio.getText().toString());
                    js.put("id", id);
                    js.put("userName", username);
                } catch (JSONException e){
                    e.printStackTrace();
                }
                JsonRequest.jsonObjectPutRequest(js, "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users", context); //send the new put request
                //todo update session variables (use Maggie's method?)
            }
        });
    }

    /**
     * method to update the ui on the buttons after changing the interests
     */
    public void updateInterestButtons(){
        String cur = interests.charAt(1) + "" + interests.charAt(2) + ""; //first interest chars
        if(InterestsUtil.getInterest(cur) != null){
            int1.setText(InterestsUtil.getInterest(cur)); //if it exists, set the text to be the interest
        }else{
            int1.setText(empty); //if it doesn't exist, set the text to be empty
        }
        cur = interests.charAt(3) + "" + interests.charAt(4) + ""; //second interest chars
        if(InterestsUtil.getInterest(cur) != null){
            int2.setText(InterestsUtil.getInterest(cur)); //if it exists, set the text to be the interest
        }else{
            int2.setText(empty); //if it doesn't exist, set the text to be empty
        }
        cur = interests.charAt(5) + "" + interests.charAt(6) + ""; //third interest chars
        if(InterestsUtil.getInterest(cur) != null){
            int3.setText(InterestsUtil.getInterest(cur)); //if it exists, set the text to be the interest
        }else{
            int3.setText(empty); //if it doesn't exist, set the text to be empty
        }
        cur = interests.charAt(7) + "" + interests.charAt(8) + ""; //fourth interest chars
        if(InterestsUtil.getInterest(cur) != null){
            int4.setText(InterestsUtil.getInterest(cur)); //if it exists, set the text to be the interest
        }else{
            int4.setText(empty); //if it doesn't exist, set the text to be empty
        }
        cur = interests.charAt(9) + "" + interests.charAt(10) + ""; //fifth interest chars
        if(InterestsUtil.getInterest(cur) != null){
            int5.setText(InterestsUtil.getInterest(cur)); //if it exists, set the text to be the interest
        }else{
            int5.setText(empty); //if it doesn't exist, set the text to be empty
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
        // Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}

