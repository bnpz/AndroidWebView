package app.bnpzenica.androidwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrl = "http://www.bnp.ba/bnp/";

        // get layout view
        webView = findViewById(R.id.webview);

        // set web client
        webView.setWebViewClient(new BnpWebViewClient());

        // load content from url
        webView.loadUrl(baseUrl);

        // configure settings (JavaScript etc.)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
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
