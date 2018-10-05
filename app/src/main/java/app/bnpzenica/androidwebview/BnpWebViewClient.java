package app.bnpzenica.androidwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BnpWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        if(url.contains("/bnp")){
            return false;
        }
        else{
            // todo: load in external app (Chrome)
            return true;
        }
    }
}
