package messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.conectar.conectar.R;

import java.util.List;

/**
 * Created by Maggie on 4/5/2018.
 * Adapter for sending/receiving messages
 */

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static Context context;

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
        Log.d("MessageListAdapter", "Entered constructor, mMessageList: " + messageList.toString());
        Log.d("MessageListAdapter", context.toString());
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
        //Log.d("MessageListAdapter", "Entered getItemViewType");
        MyMessage message = (MyMessage) mMessageList.get(position);
        //TODO fix the shared preferences thing, is currently sending nullpointerexceptions at run
        //SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        //int thisId = preferences.getInt("ID", 0);
        int thisId = 4;
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
        //Log.d("MessageListAdapter", "Entered onCreateViewHolder");
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
        //Log.d("MessageListAdapter", "Entered onBindViewHolder");
        MyMessage message = (MyMessage) mMessageList.get(position);
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
            //Log.d("SentMessageHolder", "Entered constructor");
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        /**
         * Sets the views of a message on the list item to be used in the recyclerview
         * @param message the message to be bound
         */
        void bind(MyMessage message) {
            messageText.setText(message.getMessage());
            //Log.d("SentMessageHolder", "Entered bind");
            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.formatDateTime(message.getCreatedAt()));
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
            //Log.d("ReceivedMessageHolder", "Entered constructor");
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
            Log.d("MessageListAdapter", context.toString()); //craps here if context is null
        }

        /**
         * Sets the views of a message on the list item to be used in the recyclerview
         * @param message the message to be bound
         */
        void bind(MyMessage message) {
            messageText.setText(message.getMessage());
           // Log.d("ReceivedMessageHolder", "Entered bind");

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.formatDateTime(message.getCreatedAt()));

            nameText.setText(message.getSender().getUsername());

            String profileUrl = message.getProfileUrl();
            Log.d("MessageListAdapter", "message: " + message.toString());
            Log.d("MessageListAdapter", "profileUrl: " + profileUrl);
            /*
            if(profileUrl.equals("1")){
                profileImage.setImageResource(R.drawable.fatbee_64); //set picture to bee
            }
            else if(profileUrl.equals("2")){
                profileImage.setImageResource(R.drawable.longbee_64);
            }
            */
            profileImage.setImageResource(R.drawable.shark_64);
        }
    }

    /**
     * Sets the context for use by the MessageListAdapter
     * @param context the context in which this MessageListAdapter is used
     */
    public void sendContext(Context context){
        this.context = context;
    }


}