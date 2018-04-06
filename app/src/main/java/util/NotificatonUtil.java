package util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.conectar.conectar.MainActivity;
import com.conectar.conectar.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Maggie on 4/2/2018.
 * Methods for the creation and use of notifications
 */

public class NotificatonUtil {

    private NotificationCompat.Builder notification;
    private static final int NOTIFICATION_ID = 96274;

    public void createNotification(String title, String text, Context context){
        //Base for notifications
        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true); //removes the notification after the user clicks on it
        //Build the notification
//        notification.setSmallIcon(R.id.icon_view); //sets the icon for the notification todo put this back in
        notification.setWhen(System.currentTimeMillis()); //displays the time the notification was sent
        notification.setContentTitle(title); //sets the title for the notification
        notification.setContentText(text);

        //Sets notification to bring user to app when clicked (set to main page)
        Intent intent = new Intent(context, MainActivity.class);
        //allows phone to access intents in app outside of app
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Send built notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_ID, notification.build());
    }
}
