package friends;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.conectar.conectar.R;

import util.UserUtil;

public class PendingFriendsAdapter extends BaseAdapter{
    Context context;
    Friend[] pendingFriends;
    private static LayoutInflater inflater = null;


    public PendingFriendsAdapter(Friend[] pendingFriends, Context context){
        this.context = context;
        this.pendingFriends = pendingFriends;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pendingFriends.length;
    }

    @Override
    public Object getItem(int position) {
        return pendingFriends[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView = view;
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
        if(newView == null){
            newView = inflater.inflate(R.layout.pending_list_item, null);
            final TextView username = newView.findViewById(R.id.pendingUsernameView);
            ImageView profilePicture = newView.findViewById(R.id.profilePic);
            Button addFriend = newView.findViewById(R.id.acceptFriendBtn);

            final Friend thisPending = pendingFriends[i];
            username.setText(thisPending.getUsername());
            int profilePicNum = thisPending.getProfilePicture();
            UserUtil.updateProfilePicture(profilePicNum, profilePicture);

            addFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Added " + thisPending.getUsername() + "!  Please Refresh!", Toast.LENGTH_SHORT).show();
                    Friend.makeFriend(preferences.getInt("ID", 0), thisPending.getId(), context);
                }
            });
        }

        return newView;
    }
}
