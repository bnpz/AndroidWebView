package app.bnpzenica.androidwebview;

import com.google.firebase.messaging.RemoteMessage;

public class BnpFirebaseRemoteMessage {

    private static final String data_field_url_internal = "url_internal";
    private static final String data_field_url_external = "url_external";
    private static final String data_field_production   = "production_segment";
    private String title;
    private String content;
    private String productionSegment;
    private String urlInternal;
    private String urlExternal;

    public BnpFirebaseRemoteMessage(RemoteMessage remoteMessage) {

        title = remoteMessage.getNotification().getTitle();

        content = remoteMessage.getNotification().getBody();

        if(remoteMessage.getData().get(data_field_production) != null){
            productionSegment = remoteMessage.getData().get(data_field_production);
        }
        if(remoteMessage.getData().get(data_field_url_internal) != null){
            urlInternal = remoteMessage.getData().get(data_field_url_internal);
        }
        if(remoteMessage.getData().get(data_field_url_external) != null){
            urlExternal = remoteMessage.getData().get(data_field_url_external);
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

    public String getUrlInternal() {
        return urlInternal;
    }

    public String getUrlExternal() {
        return urlExternal;
    }
}
