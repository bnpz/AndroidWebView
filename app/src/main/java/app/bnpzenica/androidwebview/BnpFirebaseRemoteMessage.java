package app.bnpzenica.androidwebview;

import com.google.firebase.messaging.RemoteMessage;

public class BnpFirebaseRemoteMessage {

    private static final String data_field_url          = "url";
    private static final String data_field_production   = "production_segment";
    private String title;
    private String content;
    private String url;
    private String productionSegment;


    public BnpFirebaseRemoteMessage(RemoteMessage remoteMessage) {

        title = remoteMessage.getNotification().getTitle();

        content = remoteMessage.getNotification().getBody();

        if(remoteMessage.getData().get(data_field_production) != null){
            productionSegment = remoteMessage.getData().get(data_field_production);
        }
        if(remoteMessage.getData().get(data_field_url) != null){
            url = remoteMessage.getData().get(data_field_url);
        }

    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getProductionSegment() {
        return productionSegment;
    }

    public String getUrl() {
        return url;
    }
}
