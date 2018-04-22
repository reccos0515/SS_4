package friends;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendsUtil {

    public static ArrayList<Friend> removeAcceptedFriends(Context context){
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
        String[] fake = new String[]{"empty"}; //do this garbage so if there aren't any the app doesn't freak
        Set<String> empty = new HashSet<>(Arrays.asList(fake));
        Set<String> friendsJSONSet = preferences.getStringSet("FRIENDJSON", empty);
        Set<String> pendingJSONSet = preferences.getStringSet("PENDINGJSON", empty);

        Friend[] friendsFriends = convertToFriend(friendsJSONSet);
        Friend[] pendingFriends = convertToFriend(pendingJSONSet);
        ArrayList<Friend> finalPending = new ArrayList<>();
        Log.d("FriendsUtil", "friendsFriends: " + Arrays.toString(friendsFriends));
        Log.d("FriendsUtil", "pendingFriends: " + Arrays.toString(pendingFriends));

        for(int i = 0; i < pendingFriends.length; i++){
            if(!Arrays.asList(friendsFriends).contains(pendingFriends[i])) {
                finalPending.add(pendingFriends[i]);
            }
        }

        return finalPending;
    }

    public static Friend[] convertToFriend(Set<String> stringJSONFriends){
        String[] fake = new String[]{"empty"};
        final Set<String> empty = new HashSet<>(Arrays.asList(fake));
        List<String> thisList = new ArrayList<>(stringJSONFriends);
        Friend[] listFriendObjects = null;

        if(thisList != empty){
            listFriendObjects = new Friend[thisList.size()];
            for(int i = 0; i < thisList.size(); i++){
                try {
                    JSONObject user = new JSONObject(thisList.get(i));
                    listFriendObjects[i] = new Friend(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return listFriendObjects;
    }

}
