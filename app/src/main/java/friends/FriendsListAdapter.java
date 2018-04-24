package friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.conectar.conectar.R;

import util.UserUtil;

public class FriendsListAdapter extends BaseAdapter {
    Context context;
    Friend[] friends;
    private static LayoutInflater inflater = null;

    public FriendsListAdapter(Friend[] friends, Context context){
        this.context = context;
        this.friends = friends;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return friends.length;
    }

    @Override
    public Object getItem(int position) {
        return friends[position];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView = view;
        if(newView == null){
            newView = inflater.inflate(R.layout.friend_list_item, null);
            TextView username = newView.findViewById(R.id.usernameView);
            TextView bio = newView.findViewById(R.id.bioView);
            ImageView profilePicture = newView.findViewById(R.id.profilePic);

            Friend thisFriend = friends[i];
            username.setText(thisFriend.getUsername());
            bio.setText(thisFriend.getBio());
            int profilePicNum = thisFriend.getProfilePicture();
            UserUtil.updateProfilePicture(profilePicNum, profilePicture);
        }
        return newView;
    }
}
