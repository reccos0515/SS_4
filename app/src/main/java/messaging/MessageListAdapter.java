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

    public MessageListAdapter(Context context, List<MyMessage> messageList) {
        mContext = context;
        mMessageList = messageList;
        Log.d("MessageListAdapter", "Entered constructor, mMessageList: " + messageList.toString());
    }

    @Override
    public int getItemCount() {
        Log.d("MessageListAdapter", "Entered getItemCount, count: " + mMessageList.size());
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    //must call sendContext(Context context) first
    @Override
    public int getItemViewType(int position) {
        Log.d("MessageListAdapter", "Entered getItemViewType");
        UserMessage message = (UserMessage) mMessageList.get(position);
        SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
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
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        Log.d("MessageListAdapter", "Entered onCreateViewHolder");
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_sent_item, viewGroup, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_recieved_item, viewGroup, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("MessageListAdapter", "Entered onBindViewHolder");
        UserMessage message = (UserMessage) mMessageList.get(position); //TODO figure out
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
        SentMessageHolder(View itemView) {
            super(itemView);
            Log.d("SentMessageHolder", "Entered constructor");
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(UserMessage message) {
            messageText.setText(message.getMessage());
            Log.d("SentMessageHolder", "Entered bind");
            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.formatDateTime(message.getCreatedAt()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        //ImageView profileImage; //TODO

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            Log.d("ReceivedMessageHolder", "Entered constructor");
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            //TODO add profile ImageView
            //profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(UserMessage message) {
            messageText.setText(message.getMessage());
            Log.d("ReceivedMessageHolder", "Entered bind");

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.formatDateTime(message.getCreatedAt()));

            nameText.setText(message.getSender().getUsername());

            // TODO Insert the profile image from the URL into the ImageView.
            //message.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }

    public void sendContext(Context context){
        this.context = context;
    }


}