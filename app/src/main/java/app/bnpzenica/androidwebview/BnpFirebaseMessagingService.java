package app.bnpzenica.androidwebview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
/*import android.media.RingtoneManager;
import android.net.Uri;*/
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class BnpFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "NOTIF";
    public static final int NOTIFICATION_ID = 1;

    public BnpFirebaseMessagingService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        BnpFirebaseRemoteMessage message = new BnpFirebaseRemoteMessage(remoteMessage);

       /* String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        Log.e(TAG, "onMessageReceived: Message Received: \n" +
                "Title: " + title + "\n" +
                "Message: " + message);

        //Log.e(TAG, remoteMessage.getData().get("key_1"));*/

        Log.e(TAG, "onMessageReceived: Message Received: \n" +
                "Title: " + message.getTitle() + "\n" +
                "Message: " + message.getContent() + "\n" +
                "Produkcija: " + message.getProductionSegment() + "\n" +
                "Int: " + message.getUrlInternal() + "\n" +
                "ext: " + message.getUrlExternal() + "\n"

        );



        //sendNotification(title,message);
        //sendBasicNotification(title,message);
    }

    private void sendBasicNotification(String title, String content){

        // NOTIFICATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setSmallIcon(R.drawable.ic_bnp_notification);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_bnp_round));

        // TEXT CONTENT
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSubText("SUB TEXT"); // appears under the text on newer devices.
        builder.setShowWhen(true);

        // TAP ACTION
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent  = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        // SEND NOTIFICATION
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
    @Override
    public void onDeletedMessages() {

    }

    private void sendNotification(String title,String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        // sound
        //Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // vibration
        long[] v = {500,1000};

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.ic_launcher_bnp)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setShowWhen(true)
                .setVibrate(v);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
