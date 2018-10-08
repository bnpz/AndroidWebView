package app.bnpzenica.androidwebview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

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

        /*Log.e(TAG, "onMessageReceived: Message Received: \n" +
                "Title: " +         message.getTitle() + "\n" +
                "Message: " +       message.getContent() + "\n" +
                "Produkcija: " +    message.getProductionSegment() + "\n" +
                "Url: " +           message.getUrl() + "\n"
        );*/

        sendBasicNotification(message);
    }

    private void sendBasicNotification(BnpFirebaseRemoteMessage message){

        // NOTIFICATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_bnp_round));
        builder.setSmallIcon(R.drawable.ic_bnp_notification);

        // SOUND
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(defaultSoundUri);

        // TEXT CONTENT
        builder.setContentTitle(message.getTitle());
        builder.setContentText(message.getContent() + message.getUrl());
        builder.setSubText(message.getProductionSegment()); // appears under the text on newer devices.
        builder.setShowWhen(true);

        // TAP ACTION
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // add notification action url to intent
        String notificationUrl = message.getUrl();
        intent.putExtra("url", notificationUrl);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

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
