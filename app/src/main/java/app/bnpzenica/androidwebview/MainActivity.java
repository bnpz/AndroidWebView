package app.bnpzenica.androidwebview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String baseUrl = "http://www.bnp.ba/bnp";
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // CHECK IF START IS FROM NOTIFICATION TAP (see: BnpFirebaseMessagingService)
        Intent i = getIntent();
        if(i.getStringExtra("url") != null){
            url = getIntent().getStringExtra("url");
        }

        // Create url to be loaded
        String finalUrl;

        if(!url.isEmpty()){
            if(url.contains(baseUrl)){
                finalUrl = url;
            }
            else{
                finalUrl = baseUrl + url;
            }
        }
        else{
            finalUrl = baseUrl;
        }

        // get layout view
        webView = findViewById(R.id.webview);

        // set web client
        webView.setWebViewClient(new BnpWebViewClient());

        // load content from url
        webView.loadUrl(finalUrl);

        // configure settings (JavaScript etc.)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Subscribe to notification topic
        FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_DEVELOP); // todo: !!! RENAME TOPIC
    }

    /**
     * Use Back Button to navigate
     */
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }

    }


}
