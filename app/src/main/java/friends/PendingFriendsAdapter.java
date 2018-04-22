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
        if(newView == null){
            newView = inflater.inflate(R.layout.pending_list_item, null);
            TextView username = (TextView) newView.findViewById(R.id.pendingUsernameView);
            ImageView profilePicture = (ImageView) newView.findViewById(R.id.profilePic);

            Friend thisPending = pendingFriends[i];
            username.setText(thisPending.getUsername());
            int profilePicNum = thisPending.getProfilePicture();
            UserUtil.updateProfilePicture(profilePicNum, profilePicture);
        }

        return newView;
    }
}
