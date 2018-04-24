package messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.conectar.conectar.R;

import java.util.List;

import util.UserUtil;

/**
 * Created by Maggie on 4/5/2018.
 * Adapter for sending/receiving messages
 */

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private List<MyMessage> mMessageList;

    /**
     * Constructor for a MessageListAdapter
     * @param context context in which this is used
     * @param messageList a list of messages (a conversation)
     */
    public MessageListAdapter(Context context, List<MyMessage> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        //Log.d("MessageListAdapter", "Entered getItemCount, count: " + mMessageList.size());
        return mMessageList.size();
    }

    /**
     * Determines the appropriate ViewType based on who sent a message, must call sendContext first
     * @param position position of the message within the conversation/recyclerview
     * @return what view type should be used for this specific message
     */
    @Override
    public int getItemViewType(int position) {
        MyMessage message = mMessageList.get(position);
        SharedPreferences preferences = mContext.getSharedPreferences("coNECTAR", mContext.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        int thisId = preferences.getInt("ID", 0);
        if (message.getSender().getUserId() == thisId) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.

    /**
     * Inflates the appropriate layout according to the ViewType
     * @param viewGroup
     * @param viewType whether or not the message should be displayed as sent or received
     * @return a new message holder based on the ViewType of the message
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_sent_item, viewGroup, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            Context thisContext = viewGroup.getContext();
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_recieved_item, viewGroup, false);
            return new ReceivedMessageHolder(view, thisContext);
        }

        return null;
    }

    /**
     * Passes the message object to a ViewHolder so that the contents can be bound to UI
     * @param holder the holder used for this message
     * @param position the position of the message within the recyclerview/conversation
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyMessage message = mMessageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        /**
         * The constructor for a SentMessageHolder, grabs the TextViews for the list item
         * @param itemView the item whose views will be grabbed
         */
        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
        }

        /**
         * Sets the views of a message on the list item to be used in the recyclerview
         * @param message the message to be bound
         */
        void bind(MyMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText(message.getCreatedAt());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        /**
         * The constructor for a ReceivedMessageHolder, grabs the TextViews and ImageView for the list item
         * @param itemView the item whose views will be grabbed
         */
        ReceivedMessageHolder(View itemView, Context context) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
            nameText = itemView.findViewById(R.id.text_message_name);
            profileImage = itemView.findViewById(R.id.profilePic);
        }

        /**
         * Sets the views of a message on the list item to be used in the recyclerview
         * @param message the message to be bound
         */
        void bind(MyMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText(message.getCreatedAt());

            nameText.setText(message.getSender().getUsername());

            int profile = message.getSender().getProfile(); //gets the profile picture of the sender

            UserUtil.updateProfilePicture(profile, profileImage);
        }
    }

}